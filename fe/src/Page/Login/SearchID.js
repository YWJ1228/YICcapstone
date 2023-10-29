import { API } from '../../Config/APIConfig';
import { DebuggingMode } from '../../Config/Config';

import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './SearchID.module.css';

import axios from 'axios';

export default function SearchID() {

    function submitHandler(event) {
        event.preventDefault();
        axios.post(`${API.FIND_ID}`, {
            name: event.target.name.value,
            birth: event.target.birth.value
        }).then(function (res) {
            alert(`아이디는 ${res.data} 입니다`);
            DebuggingMode(['검색된 아이디'], [res.data]);
        }).catch(function (err) {
            console.log(err);
            // 404 존재하지 않는 회원
        });
    }
    return (
        <div>
            <Container className={classes.logo}><Row><Col >아이디 찾기</Col></Row></Container>
            <Card className={classes.card}>
                <Card.Body>
                    <Form onSubmit={submitHandler}>
                        <Form.Group className="mb-3" controlId="formName">
                            <Form.Label>이름</Form.Label>
                            <Form.Control type="text" name="name" placeholder="홍길동" />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formBirth">
                            <Form.Label>생일</Form.Label>
                            <Form.Control type="number" name="birth" placeholder="생년월일 6자리 (ex.010101)" />
                        </Form.Group>
                        <Button className={classes['login_btn']} variant="primary" type="submit">
                            아이디 찾기
                        </Button>
                    </Form>
                </Card.Body>
            </Card>

        </div>
    );
}