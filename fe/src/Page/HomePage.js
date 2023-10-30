import { useState, useEffect } from 'react';
import { API } from '../Config/APIConfig';
import { PageConfig, DebuggingMode } from '../Config/Config';

import axios from 'axios';

import Carousel from 'react-bootstrap/Carousel';

import BannerCard from "../Component/Card/BannerCard";
import BookPreview from '../Component/Preview/BookPreview';
import VoicePreview from '../Component/Preview/VoicePreview';
import Footer from '../Component/Footer/Footer';

import classes from './HomePage.module.css';
import {getCookies} from '../Component/Cookies/LoginCookie';
import DetailStyle1 from './Detail/DetailStyle1';

export default function HomePage() {
    const [bestSellerBook, setBestSellerBook] = useState([PageConfig.EBOOK_PAGE_DEFAULT_STATE]);
    const [bestSellerVoice, setBestSellerVoice] = useState([PageConfig.VOICE_PAGE_DEFAULT_STATE]);

    useEffect(() => {
        axios.all([
            axios.get(`${API.LOAD_POPULAR_EBOOKS}`),
            axios.get(`${API.LOAD_POPULAR_VOICES}`)])
            .then(axios.spread((res1, res2) => {
                const resData1 = (res1.data.content).map((book) => ({
                    id: book.id,
                    image: book.imageUrl,
                    name: book.ebookName,
                    author: book.author,
                    price: book.price
                }));
                setBestSellerBook(resData1);
                const resData2 = (res2.data.content).map((voice) => ({
                    id: voice.id,
                    image: voice.imageUrl,
                    name: voice.celebrityName,
                    category: voice.category,
                    description: voice.comment,
                    price: voice.price
                }));
                setBestSellerVoice(resData2);
                // 디버깅
                DebuggingMode(
                    ["배너", "이 달의 책", "이 달의 TTS"],
                    [null, resData1, resData2]);
            }
            )).catch((err) => console.log(err));
    }, []);
    return (
        <>
            <div className={classes['banner-wrapper']}>
                <Carousel indicators={false}>
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
                    {...PageConfig.HOMEPAGE_TITLES[0]}
                    images={bestSellerBook} />
            </div>
            <div className={classes['book-preview']}>
                <VoicePreview
                    {...PageConfig.HOMEPAGE_TITLES[1]}
                    voices={bestSellerVoice} />
            </div>
            <div className = {classes['book-preview']}>
                <DetailStyle1 books = {bestSellerBook}/>
            </div>
            <Footer />
        </>
    );
};