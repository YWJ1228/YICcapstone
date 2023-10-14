import { useState, useEffect } from 'react';
import axios from 'axios';

import Carousel from 'react-bootstrap/Carousel';

import BannerCard from "../../Component/Card/BannerCard";

import VoicePreview from '../../Component/Preview/VoicePreview';
import classes from './VoiceShopPage.module.css';

const getBannerVoiceListAPI = "http:localhost:8080/voice-model/list?page=0"; // 배너에 올릴 TTS api
const getBestVoiceListAPI = "http://localhost:8080/voice-model/list/popularity?page=0"; // 이달의  TTS
const getOnSaleVoiceListAPI = "http://localhost:8080/voice-model/list/category?page=0&job=singer"; // 인기 TTS
const getUpdateVoiceListAPI = "http://localhost:8080/voice-model/list?page=0"; // 업데이트 된 TTS

export default function () {
    const [bestSellerVoice, setBestSellerVoice] = useState([{ // 이달의 TTS
        id: "default",
        image: "default",
        name: "default",
        job: "default",
        description: "default"
    }]);
    const [onSaleVoice, setOnSaleVoice] = useState([{ // 인기 TTS
        id: "default",
        image: "default",
        name: "default",
        job: "default",
        description: "default"
    }]);
    const [updatedVoice, setUpdatedVoice] = useState([{ // 업데이트 된 TTS
        id: "default",
        image: "default",
        name: "default",
        job: "default",
        description: "default"
    }]);
    const [bannerVoice, setBannerVoice] = useState([{ // 배너에 들어갈 정보
        id: "default",
        image: "default",
        name: "default",
        job: "default",
        description: "default"
    }]);
    const dummyInfo = [
        { title: "이달의 TTS", subtitle: "이달의 가장 인기있는 목소리를 만나보세요", voices: bestSellerVoice },
        { title: "인기 TTS", subtitle: "인기가 많은 연예인의 목소리로 오디오 북을 들어보세요", voices: onSaleVoice },
        { title: "업데이트 된 TTS", subtitle: "새로 업데이트 된 TTS를 만나보세요", voices: updatedVoice }
    ];
    useEffect(() => {
        axios.all([axios.get(getBestVoiceListAPI), axios.get(getOnSaleVoiceListAPI), axios.get(getUpdateVoiceListAPI), axios.get(getBannerVoiceListAPI)])
            .then(axios.spread((res1, res2, res3, res4) => {
                const resData = (res1.data.content).map((voice) => ({
                    id: voice.id,
                    image: voice.imageUrl,
                    name: voice.celebrityName,
                    job: voice.job,
                    description: voice.comment
                }));
                setBestSellerVoice(resData);
                const resData2 = (res2.data.content).map((voice) => ({
                    id: voice.id,
                    image: voice.imageUrl,
                    name: voice.celebrityName,
                    job: voice.job,
                    description: voice.comment
                }));
                setOnSaleVoice(resData2);
                const resData3 = (res3.data.content).map((voice) => ({
                    id: voice.id,
                    image: voice.imageUrl,
                    name: voice.celebrityName,
                    job: voice.job,
                    description: voice.comment
                }));
                setUpdatedVoice(resData3);
                const resData4 = (res4.data.content).map((voice) => ({
                    id: voice.id,
                    image: voice.imageUrl,
                    name: voice.celebrityName,
                    job: voice.job,
                    description: voice.comment
                }));
                setBannerVoice(resData4);
            })).catch((err) => console.log(err));
    }, []);
    const BannerList = bannerVoice.map((voice) => { // 배너 리스트 디자인
        return (
            <Carousel.Item>
                <BannerCard
                    imagePath={voice.image}
                    title={voice.name}
                    description={voice.description}
                />
            </Carousel.Item>
        );
    })
    const previewList = dummyInfo.map((preview) => { // 각 프리뷰 리스트 디자인
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
                <Carousel indicators={false}>
                    {BannerList}
                </Carousel>
            </div>
            {/* 리스트를 동적으로 가져옴 */}
            {previewList}
        </>
    );
};