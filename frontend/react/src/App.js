import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginFormPage from "./pages/LoginFormPage";
import 'bootstrap/dist/css/bootstrap.css';
import PhoneBookPage from "./pages/PhoneBookPage";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                    <Route exact path="/" element={<LoginFormPage/>}/>
                <Route path="/login" element={<LoginFormPage/>}/>
                <Route path="/phone-book" element={<PhoneBookPage/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
