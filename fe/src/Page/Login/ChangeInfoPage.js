import { useState } from 'react';
import { API } from '../../Config/APIConfig';
import axios from 'axios';
import { getCookies, removeCookies } from '../../Component/Cookies/LoginCookie';

import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';

import classes from './ChangeInfoPage.module.css';

export default function ChangeInfoPage() {
    const [changeForm, setChangeForm] = useState({
        password: '',
    });
    const [guideMessage, setGuideMessage] = useState({// 가이드 문구
        guidePwdText: '숫자와 알파벳을 섞어서 8~12자로 작성해주세요',
        guideEqualPwdText: ''
    });
    const [validForm, setValidForm] = useState({// 회원가입 유효성 판단
        verifyEqualPwd: false, // 비밀번호 일치 여부
        verifyPwd: false       // 비밀번호 규칙 적합 여부
    });

    function pwdChangeHanlder(event) { // 비밀번호 규칙 확인
        setChangeForm({// 비밀번호 state 설정
            ...changeForm,
            password: event.target.value.trim()
        });

        // 비밀번호 규칙 확인
        const regex = /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,20}/;
        const pwdString = event.target.value.trim();
        setValidForm({ ...validForm, verifyPwd: false });
        if (pwdString.length === 0) {
            setGuideMessage({ ...guideMessage, guidePwdText: '숫자와 알파벳을 섞어서 8~12자로 작성해주세요' });
        }
        else if (!(pwdString.length < 21 && pwdString.length >= 8)) {
            setGuideMessage({ ...guideMessage, guidePwdText: "비밀번호 길이는 8~20자입니다" });
        }
        else if (!(regex.test(pwdString))) {
            setGuideMessage({ ...guideMessage, guidePwdText: "숫자와 알파벳을 혼합하여 작성해주세요" });
        }
        else {
            setGuideMessage({ ...guideMessage, guidePwdText: "확인" });
            setValidForm({ ...validForm, verifyPwd: true });
        }
    }
    // 비밀번호 확인 
    function equalPwdChangeHandler(event) {
        if (changeForm.password === event.target.value.trim()) {
            setGuideMessage({ ...guideMessage, guideEqualPwdText: "일치합니다" });
            setValidForm({ ...validForm, verifyEqualPwd: true });
        }
        else {
            setGuideMessage({ ...guideMessage, guideEqualPwdText: "비밀번호가 다릅니다" });
            setValidForm({ ...validForm, verifyEqualPwd: false });
        }
    }


    // 닉네임 변경
    function nicknameSubmitHandler(event) {
        console.log(getCookies('refreshToken'));
        event.preventDefault();
        axios.patch(
            `${API.CHANGE_NICKNAME}`, // url
            { nickname: event.target.nickname.value.trim() }, // data
            {
                headers: {
                    Authorization: `Bearer ${getCookies('accessToken')}`,
                }
            })
            .then(function (response) {
                console.log('닉네임 변경 완료!');
                console.log(response);
            }).catch(function (err) {
                console.log(err);
            })
    };
    // 비밀번호 변경
    function passwordSubmitHandler(event) {
        event.preventDefault();
        axios.patch(`${API.CHANGE_PWD}`, {
            checkPassword: event.target.curPwd.value,
            changePassword: changeForm.password
        }, {
            headers: {
                Authorization : `Bearer ${getCookies('accessToken')}`,
            }
        }).then((res) => {
            console.log('비밀번호 변경 완료!')
            console.log(res);
        }).catch((err) => {
            console.log(err);
        })
    };

    function userWithdrawalClickHandler(event) {
        event.preventDefault();
        axios.delete(`${API.DELETE_USER}`)
            .then(function (response) {
                removeCookies('accessToken');
                removeCookies('refreshToken');
            })
            .catch((err) => { 
                console.log(err.data); 
            });
    };


    return (
        <div className={classes.wrapper}>
            <Form onSubmit={nicknameSubmitHandler}>
                <Form.Group className="mb-3" controlId="formGridName">
                    <Row className="mb-3">
                        <Form.Label>닉네임</Form.Label>
                        <Col lg={10}>
                            <Form.Control type="text" name="nickname" />
                            <Form.Text>{guideMessage.guideNicknameText}</Form.Text>
                        </Col>
                        <Col>
                            <Button type = "submit" className={classes['verify-button']}>
                                닉네임 변경
                            </Button>
                        </Col>
                    </Row>
                </Form.Group>
            </Form>
            <Form onSubmit={passwordSubmitHandler}>
                <Form.Group className="mb-3" controlId="formCheckPwd">
                    <Form.Label>현재 비밀번호 확인</Form.Label>
                    <Form.Control type="password" name="curPwd" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formPwd">
                    <Form.Label>새 비밀번호</Form.Label>
                    <Form.Control type="password" onChange={pwdChangeHanlder} />
                    <Form.Text>{guideMessage.guidePwdText}</Form.Text>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formCheckPwd">
                    <Form.Label>새 비밀번호 확인</Form.Label>
                    <Form.Control type="password" onChange={equalPwdChangeHandler} />
                    <Form.Text>{guideMessage.guideEqualPwdText}</Form.Text>
                </Form.Group>
                <Button type="submit" className={classes['submit']}>
                    비밀번호 변경
                </Button>
            </Form>

            <Button type="button" onClick = {userWithdrawalClickHandler}className={classes['submit']}>
                회원 탈퇴
            </Button>
        </div>
    );
}