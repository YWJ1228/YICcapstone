import { useEffect, useState } from 'react';
import { API } from '../../Config/APIConfig';
import { PageConfig, DebuggingMode } from '../../Config/Config';

import axios from 'axios';

import Stack from 'react-bootstrap/Stack';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';

import NativeSelect from '@mui/material/NativeSelect';

import classes from './VoicePreviewAll.module.css';
import VoicePreview from './VoicePreview';


export default function VoicePreviewAll(props) {
    const [currentCategory, setCurrentCategory] = useState('all');
    const [currentPage, setCurrentPage] = useState(1);
    const [pageCnt, setPageCnt] = useState(1);
    const [entireVoice, setEntireVoice] = useState([PageConfig.VOICE_PAGE_DEFAULT_STATE]);
    const [sortType, setSortType] = useState('0');

    useEffect(() => {
        var api = `${API.LOAD_CATEGORY_VOICE_UPLOAD}${currentCategory}&page=${currentPage - 1}`;
        if (currentCategory === 'all') {
            switch (sortType) {
                case '0': api = `${API.LOAD_VOICE_SORTBY_UPLOAD}${currentPage - 1}`; break;
                case '1': api = `${API.LOAD_VOICE_SORTBY_POPULARITY}${currentPage - 1}`; break;
                case '2': api = `${API.LOAD_VOICE_SORTBY_LOW_PRICE}${currentPage - 1}`; break;
                case '3': api = api = `${API.LOAD_VOICE_SORTBY_HIGH_PRICE}${currentPage - 1}`; break;
            }

        }
        else {
            switch (sortType) {
                case '0': api = `${API.LOAD_CATEGORY_VOICE_UPLOAD}${currentCategory}&page=${currentPage - 1}`;; break;
                case '1': api = `${API.LOAD_CATEGORY_VOICE_SORTBY_POPULARITY}${currentCategory}&page=${currentPage - 1}`; break;
                case '2': api = `${API.LOAD_CATEGORY_VOICE_SORTBY_LOW_PRICE}${currentCategory}&page=${currentPage - 1}`; break;
                case '3': api = `${API.LOAD_CATEGORY_VOICE_SORTBY_HIGH_PRICE}${currentCategory}&page=${currentPage - 1}`; break;
            }
        }
        axios.get(api)
            .then((res1) => {
                const resData = (res1.data.content).map((voice) => ({
                    id: voice.id,
                    image: voice.imageUrl,
                    name: voice.celebrityName,
                    job: voice.voiceModelCategory,
                    price: voice.price
                }));
                setEntireVoice(resData);
                setPageCnt(res1.data.totalPages);
                DebuggingMode([`${currentCategory} 페이지 `, `${currentCategory} 페이지 수`], [resData, res1.data.totalPages]);
            }).catch((err) => { 
                // console.log(err) 
            });
    }, [currentPage, currentCategory, sortType]);

    // category 변경하면 항상 첫페이지로 넘어가도록
    function CategoryClickHandler(category) {
        setCurrentCategory(category);
        setCurrentPage(1);
    }
    function SortTypeChangeHandler(event) {
        setCurrentPage(1);
        setSortType(event.target.value);
    }
    // 카테고리 리스트 시각화
    const catergoryArr = (PageConfig.VOICE_CATERGORY_LIST).map((category, idx) => {
        return (
            <Button key={idx}
                className={currentCategory !== category ? classes.category : classes['category-focus']}
                onClick={() => { CategoryClickHandler(category) }}>
                {category}
            </Button>
        );
    });
    // 페이지 버튼 배열 
    // 버튼 개수 동적으로 생성
    const buttonArr = (Array.from({ length: pageCnt }, (v, i) => i + 1)).map((pageNum, idx) => {
        return (
            <Button key={idx}
                className={pageNum === currentPage ? classes['page-button-focus'] : classes['page-button']}
                onClick={() => { setCurrentPage(pageNum) }}>
                {pageNum}
            </Button>);
    });
    const selectList = (PageConfig.SORT_TYPE).map((type, idx) => {
        return <option value={idx} key={idx}>{type}</option>
    });

    return (
        <Container>
            <Row className={classes.title}>{props.title}</Row>
            <Row className={classes['category-wrapper']}>
                <Col>
                    <Stack direction="horizontal" gap={3}>{catergoryArr}</Stack>
                </Col>
                <Col className={classes.selectBox}>
                    <NativeSelect defaultValue={0} onChange={SortTypeChangeHandler}>
                        {selectList}
                    </NativeSelect>
                </Col>
            </Row>
            <hr></hr>

            <Row><VoicePreview voices={entireVoice} once={true} /></Row>
            <Row><Col className={classes['button-wrapper']}><ButtonGroup>{buttonArr}</ButtonGroup></Col></Row>

        </Container>

    );
};