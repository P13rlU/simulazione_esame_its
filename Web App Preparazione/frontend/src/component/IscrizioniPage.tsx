import React, { useEffect, useState } from "react";
import type { Iscrizioni } from "../types/types";
import { fetchIscrizioni } from "../services/iscrizioniService";
import { useNavigate, useParams, Link } from "react-router-dom";
import {formatDateTime} from "../utils/formatDate.ts"

const IscrizioniPage: React.FC = () => {
    const { corsoId: corsoIdParam } = useParams();
    const navigate = useNavigate();

    const [iscrizioni, setIscrizioni] = useState<Iscrizioni[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const initialCorsoId = corsoIdParam ? parseInt(corsoIdParam) : undefined;
    const [corsoId, setCorsoId] = useState<number | undefined>(initialCorsoId);
    const [luogo, setLuogo] = useState<string>("");

    const loadIscrizioni = async () => {
        try {
            setLoading(true);
            const data = await fetchIscrizioni(corsoId, luogo || undefined);
            setIscrizioni(data);
            setError(null);
        } catch (err) {
            setError(
                "Errore nel caricamento delle iscrizioni" +
                (err instanceof Error ? `: ${err.message}` : "")
            );
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        if (initialCorsoId !== corsoId) setCorsoId(initialCorsoId);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [corsoIdParam]);

    useEffect(() => {
        loadIscrizioni();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <div className="container">
            <div className="header">
                <h2 className="header-title">Iscrizioni</h2>
                {initialCorsoId && (
                    <span className="badge">Id Corso: <strong>#{initialCorsoId}</strong></span>
                )}
                <div className="spacer" />
                <Link className="btn btn-ghost" to="/">← Torna alla Home</Link>
            </div>

            <div className="section">
                <form
                    className="form-row"
                    onSubmit={(e) => { e.preventDefault(); loadIscrizioni(); }}
                >
                    <input
                        className="input"
                        type="number"
                        placeholder="Corso ID"
                        value={corsoId ?? ""}
                        onChange={(e) => setCorsoId(e.target.value ? parseInt(e.target.value) : undefined)}
                        disabled={!!initialCorsoId}
                    />

                    <input
                        className="input"
                        type="text"
                        placeholder="Luogo"
                        value={luogo}
                        onChange={(e) => setLuogo(e.target.value)}
                        disabled={!!initialCorsoId}
                    />

                    <button className="btn btn-primary" type="submit">Cerca</button>

                    {initialCorsoId && (
                        <button
                            type="button"
                            className="btn"
                            onClick={() => navigate(`/corsi/${initialCorsoId}/iscrizioni/nuova`)}
                        >
                            Nuova iscrizione
                        </button>
                    )}
                </form>

                {loading && <p className="loading">Caricamento…</p>}
                {error && <p className="alert alert-error">{error}</p>}

                {!loading && !error && iscrizioni.length > 0 && (
                    <div className="table-wrap">
                        <table className="table">
                            <thead>
                            <tr>
                                <th>ID Iscrizione</th>
                                <th>Nome</th>
                                <th>Cognome</th>
                                <th>Email</th>
                                <th>Data/Ora Iscrizione</th>
                            </tr>
                            </thead>
                            <tbody>
                            {iscrizioni.map(i => (
                                <tr key={i.iscrizioneId}>
                                    <td>{i.iscrizioneId}</td>
                                    <td>{i.partecipanteNome}</td>
                                    <td>{i.partecipanteCognome}</td>
                                    <td>{i.partecipanteEmail}</td>
                                    {/*Data e Ora Formattata*/}
                                    <td>{formatDateTime(i.dataOraIscrizione)}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                )}

                {!loading && !error && iscrizioni.length === 0 && (
                    <p className="muted center">Nessuna iscrizione trovata</p>
                )}
            </div>
        </div>
    );
};

export default IscrizioniPage;
