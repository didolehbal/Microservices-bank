import logo from './logo.svg';
import 'antd/dist/antd.css';
import Navigation from './components/Navigation';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import ClientsPage from "./pages/Clients"
import Comptes from './pages/Comptes';
function App() {
  return (
    <Router>
      <Navigation/>
        <Switch>
          <Route path="/clients">
            <ClientsPage />
          </Route>
          <Route path="/comptes">
            <Comptes/>
          </Route>
          <Route path="/operations">
          </Route>
        </Switch>
    </Router>
  );
}

export default App;
