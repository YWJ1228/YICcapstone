import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';

import classes from './RegisterPage.module.css';

export default function RegisterPage() {
    return (
        <div className={classes.wrapper}>
            <Form>
                <Form.Label>이메일</Form.Label>
                <Form.Group className="mb-3" controlId="formGridCheckPassword">
                    <Form.Control type="email" />
                    <Row className="mb-3">
                        <Form.Label>인증번호</Form.Label>
                        <Col lg = {10}>
                            <Form.Control type="text" />
                        </Col>
                        <Col >
                            <Button className = {classes['verify-button']}>
                                중복확인
                            </Button>
                        </Col>
                    </Row>
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password" />
                    <Form.Label>비밀번호 확인</Form.Label>
                    <Form.Control />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formGridName">
                    <Form.Label>이름</Form.Label>
                    <Form.Control />
                    <Row className="mb-3">
                        <Form.Label>닉네임</Form.Label>
                        <Col lg = {10}>
                            <Form.Control type="text" />
                        </Col>
                        <Col>
                            <Button className = {classes['verify-button']}>
                                중복확인
                            </Button>
                        </Col>
                    </Row>
                    <Row className="mb-3">
                        <Form.Group as={Col} controlId="formGridNickname">
                            <Form.Label>생년월일</Form.Label>
                            <Form.Control type="number" />
                        </Form.Group>
                    </Row>
                </Form.Group>

                <Button type="submit" className = {classes['submit']}>
                    회원가입 완료
                </Button>
            </Form>
        </div>
    );
};

