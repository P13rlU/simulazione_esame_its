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
        <div className="container">
            <div className="header">
                <h2 className="header-title">Nuova iscrizione</h2>
                <span className="badge">Corso ID: <strong>{corsoIdParam}</strong></span>
                <div className="spacer" />
                <Link className="btn btn-ghost" to={`/corsi/${corsoId}/iscrizioni`}>
                    ← Torna alle iscrizioni del corso
                </Link>
            </div>

            {error && <p className="alert alert-error">{error}</p>}
            {okMsg && <p className="alert alert-info">{okMsg}</p>}

            <div className="section" style={{ maxWidth: 520, marginInline: "auto" }}>
                <form onSubmit={handleSubmit}>
                    <div className="form-row">
                        <input className="input" placeholder="Nome" value={nome} onChange={(e) => setNome(e.target.value)} required />
                        <input className="input" placeholder="Cognome" value={cognome} onChange={(e) => setCognome(e.target.value)} required />
                    </div>
                    <div className="form-row">
                        <input className="input" type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                    </div>
                    <div className="form-row">
                        <button className="btn btn-primary" type="submit" disabled={submitting}>
                            {submitting ? "Salvataggio..." : "Crea iscrizione"}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default CreateIscrizionePage;
