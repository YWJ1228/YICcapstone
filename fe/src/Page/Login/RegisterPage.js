import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';

import classes from './RegisterPage.module.css';
import FormGroup from 'react-bootstrap/esm/FormGroup';

import axios from 'axios';

export default function RegisterPage() {
    // 폼 데이터 형식
    const [userForm, setUserForm] = useState({
        username: 'defaultUser',
        password: 'defaultPassword',
        name: 'defaultName',
        nickname: 'defaultNickName',
        birth: 'defaultBirth',
        sex: 0
    });
    // 가이드 문구 출력 state
    const [validMessage, setValidMessage] = useState({
        validPwd: '숫자와 알파벳을 섞어서 8~12자로 작성해주세요',
        equalPwd: ''
    });

    const navigate = useNavigate();

    /** ###############################################
     *  ###인증 완료 유무 여기서 일단 수동으로 처리 요망###
     *  ################################################
     */
    const [validForm, setValidForm] = useState({
        verifyNumber: true,
        verifyNickname: true,
        verifyEqualPwd: false,
        verifyPwd : false
    });


    function formChangeHandler(event) {
        const { value, name: inputValue } = event.target;
        setUserForm({
            ...userForm,
            [inputValue]: event.target.value.trim()
        });
    }
    function validEmailHandler() {
        axios.get("http://localhost:8080/api/sign-up/username/" + userForm.username)
            .then(function (response) {
                console.log(response);
            }).catch(function (error) {
                console.log(error);
            }).then(function () {
                console.log("finish");
            });
    }
    function validNicknameHandler() {
        axios.get("http://localhost:8080/api/sign-up/nickname/" + userForm.nickname)
            .then(function (response) {
                console.log(response);
            }).catch(function (error) {
                console.log(error);
            }).then(function () {
                console.log("finish");
            });
    }

    // 비밀번호 규칙 확인
    function pwdChangeHanlder(event) {
        // 비밀번호 state 설정
        setUserForm({
            ...userForm,
            password: event.target.value.trim()
        });

        // 비밀번호 규칙 확인
        const regex = /(?=.*[0-9])(?=.*[a-zA-Z]).{8,20}/;
        const pwdString = event.target.value.trim();
        setValidForm({...validForm,verifyPwd : false});
        if (pwdString.length === 0) {
            setValidMessage({ ...validMessage, validPwd: '숫자와 알파벳을 섞어서 8~12자로 작성해주세요' });
        }
        else if (pwdString.length > 20 || pwdString.length < 8) {
            setValidMessage({ ...validMessage, validPwd: "비밀번호 길이는 8~20자입니다" });
        }
        else if (!(regex.test(pwdString))) {
            setValidMessage({ ...validMessage, validPwd: "숫자와 알파벳을 혼합하여 작성해주세요" });
        }
        else {
            setValidMessage({ ...validMessage, validPwd: "확인" });
            setValidForm({...validForm,verifyPwd : true});
        }
    }
    // 비밀번호 확인 
    function equalPwdChangeHandler(event) {
        if (userForm.password === event.target.value.trim()) {
            setValidMessage({ ...validMessage, equalPwd: "일치합니다" });
            setValidForm({...validForm,verifyEqualPwd : true});
        }
        else {
            setValidMessage({ ...validMessage, equalPwd: "비밀번호가 다릅니다" });
            setValidForm({...validForm,verifyEqualPwd : false});
        }
    }

    // form submit handler
    function submitHandler(event) {
        event.preventDefault();
        // info 를 json으로 만들어서 보내면 됨
        console.log(userForm);
        // api로 데이터 전송
        if (validForm.verifyNumber && validForm.verifyNickname && validForm.verifyPwd && validForm.verifyEqualPwd) {

            axios.post("http://localhost:8080/api/sign-up", userForm)
                .then(function (response) {
                    console.log(response);
                }).catch(function (error) {
                    console.log(error);
                }).then(function () {
                    navigate('/login');
                });
                
        }
        else if (!validForm.verifyNumber) {
            console.log('이메일에서 받은 인증코드를 입력해주세요');
        }
        else if (!validForm.verifyPwd) {
            console.log('비밀번호를 규칙에 맞게 설정해주세요');
        }
        else if(!validForm.verifyEqualPwd){
            console.log('비밀번호가 일치하지 않습니다');
        }
        else {
            console.log('닉네임 중복확인을 해주세요');
        }
    }

    return (
        <div className={classes.wrapper}>
            <Form onSubmit={submitHandler}>
                <Form.Group className="mb-3" controlId="formGridCheckEmail">
                    <Form.Label >이메일</Form.Label>
                    <Form.Control type="email" name="username" onChange={formChangeHandler} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formVerify">
                    <Row className="mb-3">
                        <Form.Label>인증번호</Form.Label>
                        <Col lg={10}><Form.Control type="text" /></Col>
                        <Col ><Button className={classes['verify-button']} type="button" name="verifyNumber" onClick={validEmailHandler}>이메일발송</Button></Col>
                    </Row>
                </Form.Group>


                <Form.Group className="mb-3" controlId="formPwd">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password" name="password" onChange={pwdChangeHanlder} />
                    <Form.Text>{validMessage.validPwd}</Form.Text>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formCheckPwd">
                    <Form.Label>비밀번호 확인</Form.Label>
                    <Form.Control type="password" onChange={equalPwdChangeHandler} />
                    <Form.Text>{validMessage.equalPwd}</Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formGridName">
                    <Form.Label>이름</Form.Label>
                    <Form.Control name="name" onChange={formChangeHandler} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formGridNickname" >
                    <Row>
                        <Form.Label >닉네임</Form.Label>
                        <Col lg={10}><Form.Control type="text" name="nickname" onChange={formChangeHandler} /></Col>
                        <Col><Button className={classes['verify-button']} type="button" name="verifyNickname" onChange={validNicknameHandler}>중복확인</Button></Col>
                    </Row>
                </Form.Group>
                <FormGroup>
                    <Row className="mb-3">
                        <Form.Group as={Col} controlId="formBirth">
                            <Form.Label>생년월일</Form.Label>
                            <Form.Control type="number" name="birth" onChange={formChangeHandler} />
                        </Form.Group>
                    </Row>
                </FormGroup>

                <Button type="submit" className={classes['submit']} >회원가입 완료</Button>
            </Form>
        </div>
    );
};

