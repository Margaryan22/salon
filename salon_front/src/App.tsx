import { Routes, Route } from 'react-router-dom';

import LogIn from './components/LogIn';
import './scss/app.scss';
import Registration from './components/Registration';
function App() {
  return (
    <>
      <div className='wrapper'>
        <Routes>
          <Route path='/login' element={<LogIn />} />
          <Route path='/registration' element={<Registration />} />
          <Route></Route>
        </Routes>
      </div>
    </>
  );
}

export default App;
