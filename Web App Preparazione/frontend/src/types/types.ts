export interface Corsi {
    corsoId: number
    titolo: string
    dataOraInizio: string
    luogo: string
    disponibilita: number
}

export interface Iscrizioni {
    iscrizioneId: number
    corsoId: number
    partecipanteNome: string
    partecipanteCognome: string
    partecipanteEmail: string
    dataOraIscrizione: string
    luogo?: string;
}

export interface CreateIscrizioneDTO {
    corsoId: number;
    partecipanteNome: string;
    partecipanteCognome: string;
    partecipanteEmail: string;
}