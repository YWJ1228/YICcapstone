import Carousel from 'react-bootstrap/Carousel';


import BannerCard from "../Component/Card/BannerCard";

import classes from './HomePage.module.css';
import BookPreview from '../Component/Preview/BookPreview';
import VoicePreview from '../Component/Preview/VoicePreview';


export default function HomePage() {
    const dummyVoice = [
        {image : "./logo192.png", name : "김채원", job : "가수", description : "르세라핌"},
        {image : "./logo192.png", name : "김채원", job : "가수", description : "르세라핌"},
        {image : "./logo192.png", name : "김채원", job : "가수", description : "르세라핌"},
        {image : "./logo192.png", name : "김채원", job : "가수", description : "르세라핌"},
        {image : "./logo192.png", name : "김채원", job : "가수", description : "르세라핌"}      
    ];
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
                    images={["./logo192.png", "./logo192.png", "./logo192.png", "./logo192.png", "./logo192.png"]}
                    link={"./"}
                />
            </div>
            <div className={classes['book-preview']}>
                <VoicePreview voices = {dummyVoice} link = {"./"}/>
            </div>
        </>
    );
};