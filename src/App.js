import { Route, Routes } from "react-router-dom";
import RegisterComponent from "./Components/Register"
import LoginComponent from "./Components/Login";
const App = () => {
  return (<>
    <Routes>
      <Route path="/" element={<LoginComponent />} />
      <Route path="/register" element={<RegisterComponent />} />
    </Routes>
  </>)
}
export default App;