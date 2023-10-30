import { useState ,useEffect} from 'react';

import { API } from '../../Config/APIConfig';
import { getCookies } from '../../Component/Cookies/LoginCookie';

import axios from 'axios';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';

import Dropdown from 'react-bootstrap/Dropdown';

import classes from './MyPageBanner.module.css';

export default function MyPageBanner(props) {
    const dummyPurchaseTTS = ["김채원", "최예나", "권은비"];
    const [selectedTTS, setSelectedTTS] = useState(0);
    const [userName, setUserName] = useState("oo");

    useEffect(()=>{
        axios.all([
            axios.get(`${API.USER_INFO}`
            ,{headers : {Authorization : `Bearer ${getCookies('accessToken')}`}})
        ]).then(axios.spread((res1)=>{
            setUserName(res1.data.name);
            // 적용중인 TTS 목록가져오기 필요
        }))
        
    },[])



    const dropItem = dummyPurchaseTTS.map((item, idx) => {
        return <Dropdown.Item eventKey={idx} className={classes['dropdown-item']}>{item}</Dropdown.Item>
    });

    return (
        <>
            <Container>
                <Row>
                    <Col xs={4}>
                        <img src="/logo192.png" className = {classes.image} />
                    </Col>
                    <Col>
                        <Row className={classes.greeting}>반갑습니다! {userName}님!</Row>
                        <Row><Button href="/changeinfo" className={classes['changeInfo-btn']} >개인정보수정</Button></Row>
                        <Row className={classes['selected-text']}>적용중인 TTS</Row>
                        <Row className={classes['dropdown-wrapper']}>
                            <Dropdown align="end" onSelect={setSelectedTTS} >
                                <Button className={classes['dropdown-content']} disabled>{dummyPurchaseTTS[selectedTTS]}</Button>
                                <Dropdown.Toggle split className={classes['dropdown-btn']} ></Dropdown.Toggle>
                                <Dropdown.Menu className={classes['dropdown-menu']}>{dropItem}</Dropdown.Menu>
                            </Dropdown>
                        </Row>
                    </Col>

                </Row>


            </Container>
            <div className={classes.divider}></div>
        </>
    );
}