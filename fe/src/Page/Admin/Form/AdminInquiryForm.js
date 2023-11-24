import Button from "react-bootstrap/esm/Button";
import Form from "react-bootstrap/Form";
import classes from "./AdminForm.module.css";
export default function AdminInquiryForm(props) {
  const setTask = props.setTask;
  return (
    <Form.Group controlId="form" className={classes.form}>
      <Form.Label>ID</Form.Label>
      <Form.Control type="text" name="id" placeholder="" disabled />
      <Form.Label>건의 유저</Form.Label>
      <Form.Control type="text" name="username" placeholder="" disabled />
      <Form.Label>제목</Form.Label>
      <Form.Control type="text" name="title" placeholder="" />
      <Form.Label>문의내용</Form.Label>
      <Form.Control type="text" name="detail" placeholder="" />
      <Form.Label>업로드 시간</Form.Label>
      <Form.Control type="text" name="uploadAt" placeholder="" />

      <Button type="submit" onClick={() => setTask(2)}>
        삭제
      </Button>
      <Button type="submit" onClick={() => setTask(3)}>
        초기화
      </Button>
    </Form.Group>
  );
}
