import { Modal, Button, ModalBody } from "react-bootstrap";

export default function AlertModal(props) {
  const handlerClose = props.func;
  const { show, message } = props.value;

  return (
    <Modal show={show} onHide={handlerClose}>
      <Modal.Body>{message}</Modal.Body>
      <Button variant="secondary" onClick={handlerClose}>
        닫기
      </Button>
    </Modal>
  );
}
