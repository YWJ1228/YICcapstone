import Carousel from 'react-bootstrap/Carousel';

import BannerCard from "../../Component/Card/BannerCard";

import VoicePreview from '../../Component/Preview/VoicePreview';
import classes from './VoiceShopPage.module.css';
/**
 * API로 가져올 정보
 *  1. 이달의 TTS 리스트
 *  2. 인기 TTS 리스트
 *  3. 업데이트 된 TTS 리스트
 * 
 *  각 책마다 가져올 정보
 *  1. id
 *  2. 이미지 경로
 *  3. 가수 이름
 *  4. 직업
 *  5. 소속
 * 
 */
export default function () {
    const dummyVoice = [
        { id: 1, image: "./logo192.png", name: "김채원", job: "가수", description: "르세라핌" },
        { id: 2, image: "./logo192.png", name: "김채원", job: "가수", description: "르세라핌" },
        { id: 3, image: "./logo192.png", name: "김채원", job: "가수", description: "르세라핌" },
        { id: 4, image: "./logo192.png", name: "김채원", job: "가수", description: "르세라핌" },
        { id: 5, image: "./logo192.png", name: "김채원", job: "가수", description: "르세라핌" }
    ];
    const dummyInfo = [
        { title: "이달의 TTS", subtitle: "이달의 가장 인기있는 목소리를 만나보세요", voices: dummyVoice },
        { title: "인기 TTS", subtitle: "인기가 많은 연예인의 목소리로 오디오 북을 들어보세요", voices: dummyVoice },
        { title: "업데이트 된 TTS", subtitle: "새로 업데이트 된 TTS를 만나보세요", voices: dummyVoice }
    ];
    const previewList = dummyInfo.map((preview) => {
        return (
            <div className={classes['voice-preview']} key={preview.title}>
                <VoicePreview
                    title={preview.title}
                    subtitle={preview.subtitle}
                    voices={preview.voices}
                    link={"./"}
                />
            </div>

        );
    });
    return (
        <>
            <div className={classes['banner-wrapper']}>
                <Carousel>
                    <Carousel.Item>
                        <BannerCard imagePath="./logo192.png" title="김채원" description="르세라핌의 김채원의 목소리로 오디오 북을 들어보세요" price="900원" salesDescription="(~9월 24일 까지)" />
                    </Carousel.Item>
                    <Carousel.Item>
                        <BannerCard imagePath="./logo192.png" title="김채원" description="르세라핌의 김채원의 목소리로 오디오 북을 들어보세요" price="900원" salesDescription="(~9월 24일 까지)" />
                    </Carousel.Item>
                </Carousel>
            </div>
            {/* 리스트를 동적으로 가져옴 */}
            {previewList}
        </>
    );
};