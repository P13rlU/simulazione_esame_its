import React, { useState } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";
import { createIscrizione } from "../services/iscrizioniService";

const CreateIscrizionePage: React.FC = () => {
    const { corsoId: corsoIdParam } = useParams();
    const navigate = useNavigate();

    const corsoId = corsoIdParam ? parseInt(corsoIdParam) : NaN;

    const [nome, setNome] = useState("");
    const [cognome, setCognome] = useState("");
    const [email, setEmail] = useState("");

    const [submitting, setSubmitting] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const [okMsg, setOkMsg] = useState<string | null>(null);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!corsoId || Number.isNaN(corsoId)) {
            setError("Corso non valido");
            return;
        }
        setError(null);
        setSubmitting(true);
        try {
            await createIscrizione({
                corsoId,
                partecipanteNome: nome.trim(),
                partecipanteCognome: cognome.trim(),
                partecipanteEmail: email.trim(),
            });
            setOkMsg("Iscrizione creata con successo");
            // Torna all’elenco iscrizioni del corso
            navigate(`/corsi/${corsoId}/iscrizioni`);
        } catch (e) {
            setError("Errore nella creazione dell'iscrizione" + (e instanceof Error ? `: ${e.message}` : ""));
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <div style={{ padding: 20 }}>
            <h2>Nuova iscrizione</h2>
            <p>Corso ID: <strong>{corsoIdParam}</strong></p>
            <p>
                <button>
                    <Link to={`/corsi/${corsoId}/iscrizioni`}>&larr; Torna alle iscrizioni del corso</Link>
                </button>
            </p>

            {error && <p style={{ color: "red" }}>{error}</p>}
            {okMsg && <p style={{ color: "green" }}>{okMsg}</p>}

            <form onSubmit={handleSubmit} style={{ maxWidth: 420 }}>
                <div style={{ marginBottom: 8 }}>
                    <label>Nome<br/>
                        <input value={nome} onChange={(e) => setNome(e.target.value)} required />
                    </label>
                </div>
                <div style={{ marginBottom: 8 }}>
                    <label>Cognome<br/>
                        <input value={cognome} onChange={(e) => setCognome(e.target.value)} required />
                    </label>
                </div>
                <div style={{ marginBottom: 8 }}>
                    <label>Email<br/>
                        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                    </label>
                </div>
                <button type="submit" disabled={submitting}>
                    {submitting ? "Salvataggio..." : "Crea iscrizione"}
                </button>
            </form>
        </div>
    );
};

export default CreateIscrizionePage;
