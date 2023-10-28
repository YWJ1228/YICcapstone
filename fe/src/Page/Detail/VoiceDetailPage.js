import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

import { API } from '../../Config/APIConfig.js';
import { DebuggingMode } from '../../Config/Config.js';

import axios from 'axios';

import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import DetailBanner from "../../Component/ProductDetail/DetailBanner.js.js";
import DetailDescription from "../../Component/ProductDetail/DetailDescription.js";
import DetailReviews from "../../Component/ProductDetail/DetailReviews.js";

import classes from './VoiceDetailPage.module.css';


export default function VoiceDetailPage() {
    const { voiceID } = useParams();
    const dummyReviews = [
        { id : 1, nickname: "usermedfg1", uploadDate: "23.08.10", text: "김채원 TTS 1번 후기입니다." },
        { id : 2, nickname: "usnsdfdfgme22", uploadDate: "23.08.12", text: "김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다." },
        { id : 3, nickname: "snmasddfge1333", uploadDate: "23.08.13", text: "김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다." },
        { id : 4, nickname: "urnm4dfg564", uploadDate: "23.08.14", text: "김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다.김채원 TTS 1번 후기입니다." },
    ];
    const [voiceInfo, setVoiceInfo] = useState({
        name: "default",
        category: "default",
        jobDescription: "default",
        image: "default",
        updatedDate: "default",
        rating: 0.00,
        price: 0,
        description: "default",
        reviews: dummyReviews
    });

    const [show, setShow] = useState(false);
    const handlerClose = () => setShow(false);
    const handlerShow = () => setShow(true);

    useEffect(() => {
        axios.get(`${API.LOAD_VOICE}/${voiceID}`)
            .then(function (response) {
                setVoiceInfo({
                    name: response.data.celebrityName,
                    category: response.data.category,
                    jobDescription: response.data.comment,
                    image: response.data.imageUrl,
                    updatedDate: response.data.uploadedAt,
                    rating: response.data.rating,
                    price: response.data.price,
                    description: response.data.comment,
                    reviews: dummyReviews
                });
                DebuggingMode("TTS 정보", response.data);
            }).catch(function(err){
                console.log(err);
                console.log("Voice detail loading error");
            })
    },[]);

    return (
        <>
        <Modal show = {show} onHide = {handlerClose}>
            <Modal.Header closeButton><Modal.Title>데모 듣기</Modal.Title></Modal.Header>
            <Modal.Body>
                <audio src = "/1.wav" controls></audio>
            </Modal.Body>
            <Modal.Footer>
                <Button variant = "secondary" onClick = {handlerClose}>닫기</Button>
            </Modal.Footer>
        </Modal>
            <div className={classes['banner-wrapper']}>
                <DetailBanner book={voiceInfo} type="voice" demoFn = {handlerShow} />
            </div>
            <div className={classes.divider}></div>
            <div className={classes['description-wrapper']}>
                <DetailDescription title="TTS 소개" description={voiceInfo.description} />
            </div>
            <div className={classes['reviews-wrapper']}>
                <DetailReviews title="후기" reviews={voiceInfo.reviews} />
            </div>
        </>
    )
}