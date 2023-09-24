import './App.css';

import 'bootstrap/dist/css/bootstrap.css';

import {useState} from 'react';

import { BrowserRouter, Routes, Route } from 'react-router-dom';

import NavigationBar from './Component/NavigationBar/NavigationBar';
import LoginPage from './Page/LoginPage';
import RegisterPage from './Page/RegisterPage';
import MyPage from './Page/MyPage';
import ChangeInfoPage from './Page/ChangeInfoPage';
import HomePage from './Page/HomePage';

// For test
import Test from './Test';

function App() {

  return (
    <>
      <NavigationBar img_src="" />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage/>}/>
          <Route path="/login" element={<LoginPage/>}/>
          <Route path="/register" element={<RegisterPage/>}/>
          <Route path="/mypage" element={<MyPage/>}/>
          <Route path="/changeinfo" element={<ChangeInfoPage/>}/>
          {/* 테스트를 위한 주소 */}
          <Route path= "/test" element = {<Test/>}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
