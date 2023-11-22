import { useState, useEffect } from "react";
import { Form, Button,Card } from "react-bootstrap";
import axios from 'axios';

import { API } from "../../Config/APIConfig";
import AlertModal from "../../Component/Modal/AlertModal/AlertModal";
import NavigationBar from "../../Component/NavigationBar/NavigationBar";

import classes from "./InquiryPage.module.css";
import { getCookies } from "../../Component/Cookies/LoginCookie";
export default function InquiryPage() {
    const [message, setMessage] = useState('');
    const [show, setShow] = useState(false);
    const showModal = () => {setShow(true)};
    const hideModal = () => {setShow(false)};
    
    useEffect(()=>{

    },[])
    function submitHandler(event){
        event.preventDefault();
        axios.post(`${API.CREATE_FEEDBACK}`,{
            title : event.target.title.value.trim(),
            detail : event.target.detail.value.trim()
        },{
            headers : {
                Authorization  : `Bearer ${getCookies('accessToken')}`
            }
        }).then((res)=>{
            showModal();
            setMessage(res.response.data.message);
        }).catch((err)=>{
            //에러 출력
        })
    }
  return (
    <>
    <AlertModal value = {{show, message}} func = {hideModal}/>
      <NavigationBar showMenuBar={true} />
      <div style={{ width: "100%", height: "8rem" }} />
      <div className = {classes.title}>건의함</div>
      <Card className={classes.card}>
        <Card.Body>
          <Form onSubmit={submitHandler}>
            <Form.Group className="mb-3" controlId="formInquiryTitle">
              <Form.Label>제목</Form.Label>
              <Form.Control type="text" placeholder="제목을 작성해주세요" name="title" />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formInquiryDetail">
              <Form.Label>내용</Form.Label>
              <Form.Control as="textarea" type="text" placeholder="문의내역을 작성해주세요" name="detail" rows={7} className={classes.textarea} />
            </Form.Group>

            <Button className={classes["submit-button"]} variant="primary" type="submit">
              제출
            </Button>
          </Form>
        </Card.Body>
      </Card>
    </>
  );
}
