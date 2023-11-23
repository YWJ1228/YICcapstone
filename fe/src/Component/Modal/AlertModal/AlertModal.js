import { Modal, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

import classes from "./AlertModal.module.css";

export default function AlertModal(props) {
  const { show, message } = props.value;
  const navigator = useNavigate();

  function handlerClose() {
    const modalClose = props.func;
    modalClose();
    if (props.navigateType !== undefined) {
      switch (props.navigateType) {
        case "home":
          navigator("/");
          break;
        case "login":
          navigator("/login");
          break;
        default:
          break;
      }
    }
  }

  return (
    <Modal show={show} onHide={handlerClose} className={classes.modal}>
      <Modal.Header>알림</Modal.Header>
      <Modal.Body>{message}</Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handlerClose}>
          닫기
        </Button>
      </Modal.Footer>
    </Modal>
  );
}
