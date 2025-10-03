import React, { useEffect, useState } from "react";
import type { Iscrizioni } from "../types/types";
import { fetchIscrizioni } from "../services/iscrizioniService";
import { useNavigate, useParams, Link } from "react-router-dom";

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
        <div style={{ padding: 20 }}>
            <h2 style={{ display: "flex", alignItems: "center", gap: 8 }}>
                Iscrizioni
                {initialCorsoId && (
                    <span
                        style={{
                            fontSize: 14,
                            padding: "2px 8px",
                            border: "1px solid #ccc",
                            borderRadius: 6,
                            alignItems: "center"
                        }}
                    >
            Id Del Corso: <strong>#{initialCorsoId}</strong>
          </span>
                )}
                <button style={{ marginLeft: "auto" }}>
                    <Link to="/" style={{ marginLeft: "auto"}}>
                        ← Torna alla Home
                    </Link>
                </button>
            </h2>

            <div style={{ marginBottom: 12 }}>
                <form  onSubmit={(e) => {
                    e.preventDefault();
                    loadIscrizioni(); // la tua funzione di fetch
                }}>
                    <input
                        type="number"
                        placeholder="Corso ID"
                        value={corsoId ?? ""}
                        onChange={(e) =>
                            setCorsoId(e.target.value ? parseInt(e.target.value) : undefined)
                        }
                        style={{ marginRight: 8 }}
                        disabled={!!initialCorsoId}
                    />
                    <input
                        type="text"
                        placeholder="Luogo"
                        value={luogo}
                        onChange={(e) => setLuogo(e.target.value)}
                        style={{ marginRight: 8 }}
                        disabled={!!initialCorsoId}
                    />
                    <button style={{ marginRight: 8 }} type="submit">
                        Cerca
                    </button>
                </form>

                {initialCorsoId && (
                    <button
                        onClick={() =>
                            navigate(`/corsi/${initialCorsoId}/iscrizioni/nuova`)
                        }
                    >
                        Nuova iscrizione
                    </button>
                )}
            </div>

            {loading && <p>Caricamento...</p>}
            {error && <p style={{ color: "red" }}>{error}</p>}

            {!loading && !error && iscrizioni.length > 0 && (
                <table
                    border={1}
                    cellPadding={5}
                    style={{ borderCollapse: "collapse", width: "100%" }}
                >
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
                    {iscrizioni.map((i) => (
                        <tr key={i.iscrizioneId}>
                            <td>{i.iscrizioneId}</td>
                            <td>{i.partecipanteNome}</td>
                            <td>{i.partecipanteCognome}</td>
                            <td>{i.partecipanteEmail}</td>
                            <td>{i.dataOraIscrizione}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}

            {!loading && !error && iscrizioni.length === 0 && (
                <p>Nessuna iscrizione trovata</p>
            )}
        </div>
    );
};

export default IscrizioniPage;
