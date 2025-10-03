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
            <h2 style={{ display: "flex", alignItems: "center", gap: 8 }}>
                Lista Corsi
                <button onClick={() => navigate("/iscrizioni")} style={{ marginLeft: "auto" }}>
                    Vai a tutte le iscrizioni
                </button>
            </h2>

            <div style={{ marginBottom: 12 }}>
                <form onSubmit={(e) => {
                    e.preventDefault();
                    load();
                }}>
                    <input
                        type="text"
                        placeholder="Filtra per titolo"
                        value={titolo}
                        onChange={(e) => setTitolo(e.target.value)}
                        style={{ marginRight: 8 }}
                    />
                    <button type="submit">Cerca</button>
                </form>
            </div>

            {loading && <p>Caricamento...</p>}
            {error && <p style={{ color: "red" }}>{error}</p>}

            {!loading && !error && (
                <ul>
                    {corsi.map((corso) => (
                        <li key={corso.corsoId} style={{ marginBottom: 10 }}>
                            <div>
                                <strong>{corso.titolo}</strong> — {corso.luogo} <br/>
                                <small>ID Corso: <strong>{corso.corsoId}</strong></small><br/>
                                Inizio: {corso.dataOraInizio} | Posti disponibili: {corso.disponibilita}
                            </div>
                            <div style={{ marginTop: 6, marginBottom: 20 }}>
                                <button
                                    onClick={() => navigate(`/corsi/${corso.corsoId}/iscrizioni`)}
                                    style={{ marginRight: 8 }}
                                >
                                    Iscrizioni Del Corso
                                </button>
                                <button onClick={() => navigate(`/corsi/${corso.corsoId}/iscrizioni/nuova`)}>
                                    Nuova iscrizione al corso
                                </button>
                            </div>
                        </li>
                    ))}
                </ul>
            )}

            {!loading && !error && corsi.length === 0 && <p>Nessun corso trovato</p>}
        </div>
    );
};

export default CorsiPage;
