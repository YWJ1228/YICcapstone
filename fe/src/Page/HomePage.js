import Carousel from 'react-bootstrap/Carousel';


import BannerCard from "../Component/Card/BannerCard";

import classes from './HomePage.module.css';
import BookPreview from '../Component/Preview/BookPreview';
import VoicePreview from '../Component/Preview/VoicePreview';


export default function HomePage() {
    const dummyVoice = [
        {id : 1 ,image : "./logo192.png", name : "김채원", job : "가수", description : "르세라핌"},
        {id : 2 ,image : "./logo192.png", name : "김채원", job : "가수", description : "르세라핌"},
        {id : 3 ,image : "./logo192.png", name : "김채원", job : "가수", description : "르세라핌"},
        {id : 4 ,image : "./logo192.png", name : "김채원", job : "가수", description : "르세라핌"},
        {id : 5 ,image : "./logo192.png", name : "김채원", job : "가수", description : "르세라핌"}      
    ];
    const dummyBook = [
        {id : 1 ,image : "./logo192.png", name : "어린왕자" ,link : "./bookDetail"},
        {id : 2 ,image : "./logo192.png", name : "어린왕자",link : "./bookDetail"},
        {id : 3 ,image : "./logo192.png", name : "어린왕자",link : "./bookDetail"},
        {id : 4 ,image : "./logo192.png", name : "어린왕자",link : "./bookDetail"},
        {id : 5 ,image : "./logo192.png", name : "어린왕자",link : "./bookDetail"},
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
                    title = "이달의 책"
                    subtitle = "이달의 가장 인기 잇는 책을 만나보세요"
                    images={dummyBook}
                    link={"./"}
                />
            </div>
            <div className={classes['book-preview']}>
                <VoicePreview voices = {dummyVoice} link = {"./"}/>
            </div>
        </>
    );
};