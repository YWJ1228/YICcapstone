import { useState } from 'react';

import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';

import classes from './RegisterPage.module.css';
import FormGroup from 'react-bootstrap/esm/FormGroup';

export default function RegisterPage() {
    // 비밀번호 규칙 확인을 위한 state
    const [pwd, setPwd] = useState();
    // 가이드 문구 출력 state
    const [validPwd, setValidPwd] = useState('숫자와 알파벳을 섞어서 8~12자로 작성해주세요');
    const [equalPwd, setEqualPwd] = useState();

    // 비밀번호 규칙 확인
    function pwdChangeHanlder(event) {
        // 비밀번호 state 설정
        setPwd(event.target.value.trim());
        // 비밀번호 규칙 확인
        const intRule = /[0-9]+/;
        const charRule = /[a-zA-Z]+/;
        const pwdString = event.target.value.trim();

        if (pwdString.length === 0) {
            setValidPwd('숫자와 알파벳을 섞어서 8~12자로 작성해주세요');
        }
        else if (pwdString.length > 16 || pwdString.length < 8) {
            setValidPwd("비밀번호 길이는 8~12자입니다");
        }
        else if (!(intRule.test(pwdString) && charRule.test(pwdString))) {
            setValidPwd("숫자와 알파벳을 혼합하여 작성해주세요");
        }
        else {
            setValidPwd("확인");
        }
    }
    // 비밀번호 확인 
    function equalPwdChangeHandler(event) {
        if (pwd === event.target.value.trim()) {
            setEqualPwd("일치합니다");
        }
        else {
            setEqualPwd("비밀번호가 다릅니다");
        }
    }
    // form submit handler
    function submitHandler(event) {
        event.preventDefault();
        // info 를 json으로 만들어서 보내면 됨
        const info = {
            email: event.target.email.value,
            password: event.target.password.value,
            name: event.target.name.value,
            nickname: event.target.nickname.value,
            birth: event.target.birth.value
        };
        console.log(info);
    }
    return (
        <div className={classes.wrapper}>
            <Form onSubmit={submitHandler}>

                <Form.Group className="mb-3" controlId="formGridCheckEmail">
                    <Form.Label>이메일</Form.Label>
                    <Form.Control type="email" name="email" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formVerify">
                    <Row className="mb-3">
                        <Form.Label>인증번호</Form.Label>
                        <Col lg={10}>
                            <Form.Control type="text" />
                        </Col>
                        <Col >
                            <Button className={classes['verify-button']}>
                                중복확인
                            </Button>
                        </Col>
                    </Row>
                </Form.Group>


                <Form.Group className="mb-3" controlId="formPwd">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password" name="password" onChange={pwdChangeHanlder} />
                    <Form.Text>{validPwd}</Form.Text>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formCheckPwd">
                    <Form.Label>비밀번호 확인</Form.Label>
                    <Form.Control type="password" onChange={equalPwdChangeHandler} />
                    <Form.Text>{equalPwd}</Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formGridName">
                    <Form.Label>이름</Form.Label>
                    <Form.Control name="name" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formGridNickname" >
                    <Row>
                        <Form.Label>닉네임</Form.Label>
                        <Col lg={10}>
                            <Form.Control type="text" name="nickname" />
                        </Col>
                        <Col>
                            <Button className={classes['verify-button']}>
                                중복확인
                            </Button>
                        </Col>
                    </Row>
                </Form.Group>
                <FormGroup>
                    <Row className="mb-3">
                        <Form.Group as={Col} controlId="formBirth">
                            <Form.Label>생년월일</Form.Label>
                            <Form.Control type="number" name="birth" />
                        </Form.Group>
                    </Row>
                </FormGroup>

                <Button type="submit" className={classes['submit']} >
                    회원가입 완료
                </Button>
            </Form>
        </div>
    );
};

