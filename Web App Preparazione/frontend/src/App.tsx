import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import HomePage from "./pages/Home";
import IscrizioniPage from "./component/IscrizioniPage";
import CreateIscrizionePage from "./component/CreateIscrizionePage";

export default function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/iscrizioni" element={<IscrizioniPage/>} />
                <Route path="/corsi/:corsoId/iscrizioni" element={<IscrizioniPage/>} />
                <Route path="/corsi/:corsoId/iscrizioni/nuova" element={<CreateIscrizionePage/>} />
                <Route path="*" element={<Navigate to="/" replace />} />
            </Routes>
        </BrowserRouter>
    );
}