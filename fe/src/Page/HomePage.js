import { useState, useEffect } from 'react';

import Carousel from 'react-bootstrap/Carousel';

import BannerCard from "../Component/Card/BannerCard";
import BookPreview from '../Component/Preview/BookPreview';
import VoicePreview from '../Component/Preview/VoicePreview';

import classes from './HomePage.module.css';

import axios from 'axios';
// import Footer from '../Component/Footer/Footer';

// 헷갈려서 description으로 바꾸는거 제안




export default function HomePage() {
    const [bestSellerBook, setBestSellerBook] = useState([{
        id: "default",
        image: "default",
        name: "default",
        author: "default",
    }]);

    const dummyVoice = [
        { id: 1, image: "./logo192.png", name: "김채원", job: "가수", description: "르세라핌" },
        { id: 2, image: "./logo192.png", name: "김채원", job: "가수", description: "르세라핌" },
        { id: 3, image: "./logo192.png", name: "김채원", job: "가수", description: "르세라핌" },
        { id: 4, image: "./logo192.png", name: "김채원", job: "가수", description: "르세라핌" },
        { id: 5, image: "./logo192.png", name: "김채원", job: "가수", description: "르세라핌" }
    ];
    const dummyBook = [
        { id: 1, image: "./logo192.png", name: "어린왕자", author: "생텍쥐페리", link: "./bookDetail" },
        { id: 2, image: "./logo192.png", name: "어린왕자", author: "생텍쥐페리", link: "./bookDetail" },
        { id: 3, image: "./logo192.png", name: "어린왕자", author: "생텍쥐페리", link: "./bookDetail" },
        { id: 4, image: "./logo192.png", name: "어린왕자", author: "생텍쥐페리", link: "./bookDetail" },
        { id: 5, image: "./logo192.png", name: "어린왕자", author: "생텍쥐페리", link: "./bookDetail" },
    ];

    useEffect(() => {
        axios.get("http://localhost:8080/ebook/list?page=0")
        .then(function (response) {
            // 응답 데이터 콘솔 출력
            console.log(response);
            const resData = (response.data.content).map((book) => ({
                id: book.id,
                image: book.imageUrl,
                name: book.ebookName,
                author: book.author
            }));
            setBestSellerBook(resData);
            console.log(resData);
        }).catch(function (error) {
            console.log(error);
        })},[]
    );
    return (
        <>
            <div className={classes['banner-wrapper']}>
                <Carousel>
                    <Carousel.Item>
                        <BannerCard imagePath="./logo192.png" title="어린왕자" description="생텍쥐페리 작가의 희대의 명작!" price="900원" salesDescription="(~9월 24일 까지)" />
                    </Carousel.Item>
                    <Carousel.Item>
                        <BannerCard imagePath="./logo192.png" title="어린왕자" description="생텍쥐페리 작가의 희대의 명작!" price="900원" salesDescription="(~9월 24일 까지)" />
                    </Carousel.Item>
                </Carousel>
            </div>
            <div className={classes['book-preview']}>
                <BookPreview
                    title="이달의 책"
                    subtitle="이달의 가장 인기 있는 책을 만나보세요"
                    images={bestSellerBook}
                    link={"./bookShop"} />
            </div>
            <div className={classes['book-preview']}>
                <VoicePreview
                    title="이달의 TTS"
                    subtitle="이달의 가장 인기 있는 TTS를 만나보세요"
                    voices={dummyVoice}
                    link={"./voiceShop"} />
            </div>
            {/* 구현 시간남으면 할 예정 */}
            {/* <Footer /> */}
        </>
    );
};