import './App.scss';
import {HashRouter as Router, Route, Switch} from 'react-router-dom';
import { HomePage } from './pages/HomePage';

export function App() {
  

  return (
    <div className="App">
      <Router>
        <Switch>
          <Route path="/">
            <HomePage />
          </Route>

        </Switch>
      </Router>
    </div>
  );
}

export default App;
