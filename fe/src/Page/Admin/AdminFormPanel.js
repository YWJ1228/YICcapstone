import { useContext, useState } from "react";
import { VoiceContext } from "../../Component/Context/AdminContext";
import axios from "axios";
import { API } from "../../Config/APIConfig";
import Button from "react-bootstrap/esm/Button";

import Form from "react-bootstrap/Form";

import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
export default function AdminFormPanel() {
  const { voice, setVoice } = useContext(VoiceContext);
  const [task, setTask] = useState(0);
  function submitHandler(event) {
    event.preventDefault();
    switch (task) {
      case 0:
        addVoiceHandler(event.target);
        break;
      case 1:
        changeVoiceHandler(event.target);
        break;
      case 2:
        deleteVoiceHandler(event.target);
        break;
      case 3:
        initFormHandler(event.target);
        break;
      case 4:
        loadVoiceHandler(event.target);
        break;
    }
  }
  function loadVoiceHandler(form) {
    form.id.value = voice.id;
    form.name.value = voice.name;
    form.price.value = voice.price;
    form.comment.value = voice.description;
    form.category.value = voice.job;
    form.imageUrl.value = voice.image;
    form.voiceUrl.value = voice.voiceUrl;
    form.demoUrl.value = voice.demoUrl;
  }
  function addVoiceHandler(form) {
    axios
      .post(`${API.ADMIN_ADD_VOICE}`, {
        voiceModelUrl: form.voiceUrl.value.trim(),
        celebrityName: form.name.value.trim(),
        price: form.price.value,
        imageUrl: form.imageUrl.value.trim(),
        comment: form.comment.value.trim(),
        sampleUrl: form.demoUrl.value.trim(),
        category: form.category.value.trim(),
      })
      .then((res) => {
        console.log(res);
        console.log("추가 완료");
        initFormHandler(form);
      })
      .catch((err) => {
        console.log(err);
      });
  }

  function changeVoiceHandler(form) {
    axios
      .put(`${API.ADMIN_ADD_VOICE}/${voice.id}`, {
        voiceModelUrl: form.voiceUrl.value.trim(),
        celebrityName: form.name.value.trim(),
        price: form.price.value,
        imageUrl: form.imageUrl.value.trim(),
        comment: form.comment.value.trim(),
        sampleUrl: form.demoUrl.value.trim(),
        category: form.category.value.trim(),
      })
      .then((res) => {
        console.log(res);
        console.log("수정완료");
        initFormHandler(form);
      })
      .catch((err) => {
        console.log(err);
      });
  }
  function deleteVoiceHandler(form) {
    axios
      .delete(`${API.ADMIN_ADD_VOICE}/${voice.id}`)
      .then((res) => {
        console.log(res);
        console.log("삭제 완료");
        initFormHandler(form);
      })
      .catch((err) => {
        console.log(err);
      });
  }
  function initFormHandler(form) {
    form.id.value = "";
    form.name.value = "";
    form.price.value = "";
    form.comment.value = "";
    form.category.value = "";
    form.imageUrl.value = "";
    form.voiceUrl.value = "";
    form.demoUrl.value = "";
  }
  return (
    <Form onSubmit={submitHandler}>
      <Form.Group className="mb-3" controlId="form">
        <Form.Label>상품 번호</Form.Label>
        <Form.Control type="text" name="id" placeholder="" disabled />
        <Form.Label>연예인 이름</Form.Label>
        <Form.Control type="text" name="name" placeholder="" />
        <Form.Label>가격</Form.Label>
        <Form.Control type="number" name="price" placeholder="" />
        <Form.Label>상세설명</Form.Label>
        <Form.Control type="text" name="comment" placeholder="" />
        <Form.Label>카테고리</Form.Label>
        <Form.Control type="text" name="category" placeholder="" />
        <Form.Label>이미지 위치</Form.Label>
        <Form.Control type="text" name="imageUrl" placeholder="" />
        <Form.Label>음성모델 위치</Form.Label>
        <Form.Control type="text" name="voiceUrl" placeholder="" />
        <Form.Label>데모 위치</Form.Label>
        <Form.Control type="text" name="demoUrl" placeholder="" />
        <Button type="submit" onClick={() => setTask(0)}>
          생성
        </Button>
        <Button type="submit" onClick={() => setTask(1)}>
          수정
        </Button>
        <Button type="submit" onClick={() => setTask(2)}>
          삭제
        </Button>
        <Button type="submit" onClick={() => setTask(3)}>
          초기화
        </Button>
        <Button type="submit" onClick={() => setTask(4)}>
          불러오기
        </Button>
      </Form.Group>
    </Form>
  );
}
