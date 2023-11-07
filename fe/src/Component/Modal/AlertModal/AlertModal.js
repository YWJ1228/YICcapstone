import { useContext } from "react";
import { Modal, Button, ModalBody } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

export default function AlertModal(props) {
  const { show, message } = props.value;
  const navigator = useNavigate();

  function handlerClose() {
    const modalClose = props.func;
    modalClose();
    if ( props.navigateType !== undefined){
      switch(props.navigateType){
        case 'home' : navigator('/'); break;
        case 'login' : navigator('/login'); break;
        default : console.log('Navigate 주소 입력 오류'); break;
      }
    }
  }

  return (
    <Modal show={show} onHide={handlerClose}>
      <Modal.Body>{message}</Modal.Body>
      <Button variant="secondary" onClick={handlerClose}>
        닫기
      </Button>
    </Modal>
  );
}
