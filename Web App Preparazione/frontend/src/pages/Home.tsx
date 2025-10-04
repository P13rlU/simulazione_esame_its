import CorsiPage from "../component/CorsiPage.tsx";

export default function HomePage() {
    return (
        <div className="container">
            <div className="header">
                <h1 className="header-title">Home</h1>
            </div>
            <div className="section">
                <CorsiPage/>
            </div>
        </div>
    );
}