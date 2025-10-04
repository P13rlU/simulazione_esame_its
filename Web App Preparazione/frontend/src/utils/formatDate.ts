export function formatDateTime(isoString: string): string {
    try {
        const date = new Date(isoString);
        return new Intl.DateTimeFormat("it-IT", {
            day: "2-digit",
            month: "2-digit",
            year: "numeric",
            hour: "2-digit",
            minute: "2-digit"
        }).format(date);
    } catch {
        return isoString; // fallback se la data non è valida
    }
}
