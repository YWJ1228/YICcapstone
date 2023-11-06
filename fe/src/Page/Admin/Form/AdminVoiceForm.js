import Button from "react-bootstrap/esm/Button";
import Form from "react-bootstrap/Form";
import classes from "./AdminForm.module.css";
export default function AdminVoiceForm(props) {
  const setTask = props.setTask;
  return (
    <Form.Group controlId="form" className={classes.form}>
      <Form.Label>상품 번호</Form.Label>
      <Form.Control type="text" name="id" placeholder="" disabled />
      <Form.Label>카테고리</Form.Label>
      <Form.Control type="text" name="category" placeholder="" />
      <Form.Label>연예인 이름</Form.Label>
      <Form.Control type="text" name="name" placeholder="" />
      <Form.Label>상세설명</Form.Label>
      <Form.Control type="text" name="comment" placeholder="" />
      <Form.Label>이미지 위치</Form.Label>
      <Form.Control type="text" name="imageUrl" placeholder="" />
      <Form.Label>음성모델 위치</Form.Label>
      <Form.Control type="text" name="voiceUrl" placeholder="" />
      <Form.Label>데모 위치</Form.Label>
      <Form.Control type="text" name="demoUrl" placeholder="" />
      <Form.Label>가격</Form.Label>
      <Form.Control type="number" name="price" placeholder="" />
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
    </Form.Group>
  );
}
