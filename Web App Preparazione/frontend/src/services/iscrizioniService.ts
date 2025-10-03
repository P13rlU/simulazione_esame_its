import type { Iscrizioni, CreateIscrizioneDTO } from "../types/types";

const API_URL = "http://localhost:8080/enrollments";

export async function fetchIscrizioni(corsoId?: number, luogo?: string): Promise<Iscrizioni[]> {
    const params = new URLSearchParams();
    if (corsoId !== undefined) params.append("corsoId", String(corsoId));
    if (luogo) params.append("luogo", luogo);
    const url = params.toString() ? `${API_URL}?${params.toString()}` : API_URL;

    const response = await fetch(url);
    if (!response.ok) throw new Error("Errore nel recupero delle iscrizioni");
    return await response.json();
}

export async function createIscrizione(payload: CreateIscrizioneDTO): Promise<Iscrizioni> {
    const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
    });
    if (!response.ok) {
        const text = await response.text().catch(() => "");
        throw new Error(`Errore nella creazione dell'iscrizione${text ? `: ${text}` : ""}`);
    }
    return await response.json();
}

