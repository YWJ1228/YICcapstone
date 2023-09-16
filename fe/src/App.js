import './App.css';

import 'bootstrap/dist/css/bootstrap.css';

import { BrowserRouter, Routes, Route } from 'react-router-dom';

import NavigationBar from './Component/NavigationBar/NavigationBar';
import LoginPage from './Page/LoginPage';
import RegisterPage from './Page/RegisterPage';
import MyPage from './Page/MyPage';
import ChangeInfoPage from './Page/ChangeInfoPage';
// npm install -g react-scripts
// npm install react-bootstrap bootstrap

function App() {
  return (
    <>
      <NavigationBar img_src="" />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<div>Home</div>}/>
          <Route path="/login" element={<LoginPage/>}/>
          <Route path="/register" element={<RegisterPage/>}/>
          <Route path="/mypage" element={<MyPage/>}/>
          <Route path="/changeinfo" element={<ChangeInfoPage/>}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
