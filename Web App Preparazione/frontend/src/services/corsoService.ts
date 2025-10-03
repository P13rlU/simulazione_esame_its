import type { Corsi } from "../types/types";

const API_URL = "http://localhost:8080/courses";

export async function fetchCorsi(params?: { titolo?: string;}): Promise<Corsi[]> {
    const usp = new URLSearchParams();
    if (params?.titolo) usp.append("titolo", params.titolo);
    const url = usp.toString() ? `${API_URL}?${usp.toString()}` : API_URL;

    const response = await fetch(url);
    if (!response.ok) throw new Error("Errore nel recupero dei corsi");
    return await response.json();
}