import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginFormPage from "./pages/LoginFormPage";
import 'bootstrap/dist/css/bootstrap.css';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route>
                    <Route exact path="/" element={<LoginFormPage/>}/>
                    <Route path="/login" element={<LoginFormPage/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
