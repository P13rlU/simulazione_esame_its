import React, { useEffect, useState } from "react";
import type { Corsi } from "../types/types";
import { fetchCorsi } from "../services/corsoService";
import { useNavigate } from "react-router-dom";

const CorsiPage: React.FC = () => {
    const [corsi, setCorsi] = useState<Corsi[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const [titolo, setTitolo] = useState<string>("");

    const navigate = useNavigate();

    const load = async () => {
        try {
            setLoading(true);
            const data = await fetchCorsi({
                titolo: titolo || undefined,
            });
            setCorsi(data);
            setError(null);
        } catch (e) {
            setError("Errore nel caricamento dei corsi" + (e instanceof Error ? `: ${e.message}` : ""));
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => { load(); }, []);


    return (
        <div>
            <div className="header">
                <h2 className="header-title">Lista Corsi</h2>
                <div className="spacer" />
                <button className="btn btn-ghost" onClick={() => navigate("/iscrizioni")}>
                    Vai a tutte le iscrizioni
                </button>
            </div>

            <div className="section">
                <form
                    className="form-row"
                    onSubmit={(e) => { e.preventDefault(); load(); }}
                >
                    <input
                        className="input"
                        type="text"
                        placeholder="Filtra per titolo"
                        value={titolo}
                        onChange={(e) => setTitolo(e.target.value)}
                    />
                    <button className="btn btn-primary" type="submit">Cerca</button>
                </form>

                {loading && <p className="loading">Caricamento…</p>}
                {error && <p className="alert alert-error">{error}</p>}

                {!loading && !error && corsi.length > 0 && (
                    <div className="grid">
                        {corsi.map((corso) => (
                            <div key={corso.corsoId} className="card">
                                <div>
                                    <div className="card-title">{corso.titolo}</div>
                                    <div className="card-meta">
                                        {corso.luogo} · <span className="badge">ID #{corso.corsoId}</span>
                                    </div>
                                    <div className="card-meta">
                                        Inizio: {corso.dataOraInizio} · Posti disponibili: {corso.disponibilita}
                                    </div>
                                </div>

                                <div className="card-actions">
                                    <button
                                        className="btn"
                                        onClick={() => navigate(`/corsi/${corso.corsoId}/iscrizioni`)}
                                    >
                                        Iscrizioni del corso
                                    </button>
                                    <button
                                        className="btn btn-primary"
                                        onClick={() => navigate(`/corsi/${corso.corsoId}/iscrizioni/nuova`)}
                                    >
                                        Nuova iscrizione
                                    </button>
                                </div>
                            </div>
                        ))}
                    </div>
                )}

                {!loading && !error && corsi.length === 0 && (
                    <p className="muted center">Nessun corso trovato</p>
                )}
            </div>
        </div>
    );
};

export default CorsiPage;
