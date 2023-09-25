import './App.css';

import 'bootstrap/dist/css/bootstrap.css';

import { useState } from 'react';

import { BrowserRouter, Routes, Route } from 'react-router-dom';

import NavigationBar from './Component/NavigationBar/NavigationBar';
import LoginPage from './Page/Login/LoginPage';
import RegisterPage from './Page/Login/RegisterPage';
import MyPage from './Page/MyPage';
import ChangeInfoPage from './Page/Login/ChangeInfoPage';
import HomePage from './Page/HomePage';
import SearchPwd from './Page/Login/SearchPwd';
import SearchID from './Page/Login/SearchID';
import BookShopPage from './Page/Shop/BookShopPage';
import VoiceShopPage from './Page/Shop/VoiceShopPage';

// For test
import Test from './Test';
import BookDetailPage from './Page/Detail/BookDetailPage';
import VoiceDetailPage from './Page/Detail/VoiceDetailPage';


function App() {

  return (
    <>
      <NavigationBar img_src="logo.png" />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/changeinfo" element={<ChangeInfoPage />} />
          <Route path="/search-pwd" element={<SearchPwd />} />
          <Route path="/search-id" element={<SearchID />} />
          <Route path="/bookShop" element={<BookShopPage/>}/>
          <Route path="/voiceShop" element={<VoiceShopPage/>}/>
          <Route path="/bookDetail" element={<BookDetailPage/>}/>
          <Route path="/voiceDetail" element={<VoiceDetailPage/>}/>
          {/* 테스트를 위한 주소 */}
          <Route path="/test" element={<Test />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
