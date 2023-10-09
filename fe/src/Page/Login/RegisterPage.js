import { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';

import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';

import classes from './RegisterPage.module.css';
import FormGroup from 'react-bootstrap/esm/FormGroup';

import axios from 'axios';
import SexBtn from '../../Component/Button/SexBtn';
import StyleChangeRef from '../../Ref/StyleChangeRef';
/**
 * #####################################
 * # 1. useState 선언                 
 * # 2. changeHandler              
 * # 3. valid check function
 * # 4. Form submit handler
 * ######################################
 */

export default function RegisterPage() {
    const myRef = useRef([]);

    // 백엔드로 보내는 json 형식
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

    /** ##############################################
     *  ### 일단 일정 규칙이 안맞아도 페이지 넘기려면 ###
     *  ### 해당 아래 코드에서 false를 true로 변경   ###
     *  ### 인증번호는 제대로 입력해주어야함          ###
     *  ##############################################
     */
    const [validForm, setValidForm] = useState({
        verifyText: false,      // 서버로부터 받은 인증번호를 저장할 state
        verifyNickname: false,  // 닉네임 인증 여부
        verifyEqualPwd: false, // 비밀번호 일치 여부
        verifyPwd: false       // 비밀번호 규칙 적합 여부
    });

    // Form change Handler for form events
    function formChangeHandler(event) {
        const { value, name: inputValue } = event.target;
        setUserForm({
            ...userForm,
            [inputValue]: event.target.value.trim()
        });
    }
    // Check validation of email 
    function validEmailHandler() {
        axios.get("http://localhost:8080/api/sign-up/username/" + userForm.username)
            .then(function (response) {
                // 응답 데이터 콘솔 출력
                console.log(response);

                // 정답 인증번호 저장
                setValidForm({ ...validForm, verifyText: response.data });
            }).catch(function (error) {
                console.log(error);
            });
    }
    function validNicknameHandler() {
        axios.get("http://localhost:8080/api/sign-up/nickname/" + userForm.nickname)
            .then(function (response) {
                console.log('valid nickname');
                if(response.data === '사용 가능한 닉네임입니다!'){
                    setValidForm({...validForm, verifyNickname : true});
                    console.log(validForm);
                }
            }).catch(function (error) {
                console.log(error);
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
        setValidForm({ ...validForm, verifyPwd: false });
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
            setValidForm({ ...validForm, verifyPwd: true });
        }
    }
    // 비밀번호 확인 
    function equalPwdChangeHandler(event) {
        if (userForm.password === event.target.value.trim()) {
            setValidMessage({ ...validMessage, equalPwd: "일치합니다" });
            setValidForm({ ...validForm, verifyEqualPwd: true });
        }
        else {
            setValidMessage({ ...validMessage, equalPwd: "비밀번호가 다릅니다" });
            setValidForm({ ...validForm, verifyEqualPwd: false });
        }
    }

    // form submit handler
    function submitHandler(event) {
        event.preventDefault();

        // api로 데이터 전송
        if (validForm.verifyText===(event.target.verifyText.value) && validForm.verifyNickname && validForm.verifyPwd && validForm.verifyEqualPwd) {
            console.log('Login Succeeded');
            axios.post("http://localhost:8080/api/sign-up", userForm)
                .then(function (response) {
                    console.log(response);
                    navigate('/login');
                }).catch(function (error) {
                    console.log(error);
                    console.log("API 보내는 도중에 error 발생");
                })

        }
        // 이메일 유효성 검사
        const conditions = [
            event.target.username.value !== '', // 이메일
            validForm.verifyText === event.target.verifyText.value, // 인증번호
            validForm.verifyPwd, // 비밀번호
            validForm.verifyEqualPwd, // 비밀번호 확인
            event.target.name.value !== '',  // 이름
            validForm.verifyNickname, // 닉네임
            event.target.birth.value !== '' // 생년월일
        ];
        conditions.map((cond,index)=>{
            StyleChangeRef(myRef, index, cond);
        })
    }

    return (
        <div className={classes.wrapper}>
            <Form onSubmit={submitHandler}>
                <Form.Group className="mb-3" controlId="formGridCheckEmail">
                    <Form.Label >아이디 생성</Form.Label>
                    <Form.Control type="email" name="username" onChange={formChangeHandler} placeholder='이메일' className={classes.inputBox}
                        ref={el => myRef.current[0] = el} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formVerify">
                    <Row className="mb-3">
                        <Col lg={10}><Form.Control type="text" placeholder='인증번호' name="verifyText" className={classes.inputBox} ref={el => myRef.current[1] = el} /></Col>
                        <Col><Button className={classes['verify-button']} type="button"  onClick={validEmailHandler} >이메일발송</Button></Col>
                    </Row>
                </Form.Group>


                <Form.Group className="mb-3" controlId="formPwd">
                    <Form.Control type="password" name="password" onChange={pwdChangeHanlder} placeholder='비밀번호' className={classes.inputBox} ref={el => myRef.current[2] = el} />
                    <Form.Text>{validMessage.validPwd}</Form.Text>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formCheckPwd">
                    <Form.Control type="password" name = "checkPassword" onChange={equalPwdChangeHandler} placeholder='비밀번호 확인' className={classes.inputBox} ref={el => myRef.current[3] = el} />
                    <Form.Text>{validMessage.equalPwd}</Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formGridName">
                    <Row>
                        <Form.Label>개인정보</Form.Label>
                        <Col><Form.Control name="name" onChange={formChangeHandler} placeholder='성명' className={classes.inputBox} ref={el => myRef.current[4] = el} /></Col>
                        <Col><Button className={classes['verify-button']} type="button"  onChange={validNicknameHandler}>성별</Button></Col>
                    </Row>

                </Form.Group>
                <Form.Group className="mb-3" controlId="formGridNickname" >
                    <Row>
                        <Col lg={10}><Form.Control type="text" name="nickname" onChange={formChangeHandler} placeholder='닉네임' className={classes.inputBox} ref={el => myRef.current[5] = el} /></Col>
                        <Col><Button className={classes['verify-button']} type="button" name="verifyNickname" onClick={validNicknameHandler}>중복확인</Button></Col>
                    </Row>
                </Form.Group>
                <FormGroup>
                    <Row className="mb-3">
                        <Form.Group as={Col} controlId="formBirth">
                            <Form.Control type="number" name="birth" onChange={formChangeHandler} placeholder='생년월일' className={classes.inputBox} ref={el => myRef.current[6] = el} />
                        </Form.Group>
                    </Row>
                </FormGroup>

                <Button type="submit" className={classes['submit']} >회원가입 완료</Button>
            </Form>
            {/* <SexBtn/> */}
        </div>
    );
};

