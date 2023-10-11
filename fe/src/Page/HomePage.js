import { useState, useEffect } from 'react';

import Carousel from 'react-bootstrap/Carousel';

import BannerCard from "../Component/Card/BannerCard";
import BookPreview from '../Component/Preview/BookPreview';
import VoicePreview from '../Component/Preview/VoicePreview';

import classes from './HomePage.module.css';

import axios from 'axios';

const getBestEbookListAPI = "http://localhost:8080/ebook/list?page=0";
const getBestVoiceAPI = "http://localhost:8080/voice-model/list?page=0";

export default function HomePage() {
    const [bestSellerBook, setBestSellerBook] = useState([{
        id: "default",
        image: "default",
        name: "default",
        author: "default",
    }]);
    const [bestSellerVoice, setBestSellerVoice] = useState([{
        id : "default",
        image : "default",
        name : "default",
        job : "default",
        description : "default"
    }]);

    useEffect(() => {
        axios.get(getBestEbookListAPI)
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
        });
        axios.get(getBestVoiceAPI)
        .then(function (response) {
            // 응답 데이터 콘솔 출력
            console.log(response);
            const resData = (response.data.content).map((voice) => ({
                id : voice.id,
                image : voice.imageUrl,
                name : voice.celebrityName,
                job : voice.job,
                description : voice.comment
            }));
            setBestSellerVoice(resData);
        }).catch(function (error) {
            console.log(error);
        })
        
    },[]
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
                    voices={bestSellerVoice}
                    link={"./voiceShop"} />
            </div>
            {/* 구현 시간남으면 할 예정 */}
            {/* <Footer /> */}
        </>
    );
};