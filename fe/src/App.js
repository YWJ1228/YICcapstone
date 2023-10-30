import './App.css';

import 'bootstrap/dist/css/bootstrap.css';

import { BrowserRouter, Routes, Route } from 'react-router-dom';



import NavigationBar from './Component/NavigationBar/NavigationBar';
import LoginPage from './Page/Login/LoginPage';
import RegisterPage from './Page/Login/RegisterPage';
import MyPage from './Page/MyPage/MyPage';
import ChangeInfoPage from './Page/Login/ChangeInfoPage';
import HomePage from './Page/HomePage';
import SearchPwd from './Page/Login/SearchPwd';
import SearchID from './Page/Login/SearchID';
import BookShopPage from './Page/Shop/BookShopPage';
import VoiceShopPage from './Page/Shop/VoiceShopPage';
import AdminPage from './Page/Admin/AdminPage';


import BookDetailPage from './Page/Detail/BookDetailPage';
import VoiceDetailPage from './Page/Detail/VoiceDetailPage';
import ChangePwd from './Page/Login/ChangePwd';

// 작업 to-do list
// 1. 배너 Item을 api로 가져오는 것 구현해야함



function App() {

  return (
    <>
      <NavigationBar img_src="logo.png"/>
      {/* padblock */}
      <div style={{ width: '100%', height: '6rem' }} />
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/admin" element={<AdminPage/>} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/changeinfo" element={<ChangeInfoPage />} />
            <Route path="/search-pwd" element={<SearchPwd />} />
            <Route path="/change-pwd" element={<ChangePwd/>}/>
            <Route path="/search-id" element={<SearchID />} />
            <Route path="/bookShop" element={<BookShopPage />} />
            <Route path="/voiceShop" element={<VoiceShopPage />} />
            <Route path="/bookDetail/:ebookID" element={<BookDetailPage />} />
            <Route path="/voiceDetail/:voiceID" element={<VoiceDetailPage />} />

          </Routes>
        </BrowserRouter>
    </>
  );
}

export default App;
