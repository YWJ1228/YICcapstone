import { creditConfig } from "../../Config/Config";
const IMP = window.IMP;
IMP.init(creditConfig.imp);
export const onClickPayment = (info) => {
    const data = {
        pg: creditConfig.pg, // PG사
        pay_method: "card", // 결제수단
        merchant_uid: info.productId, // 주문번호
        amount: info.price, // 결제금액
        name: info.name, // 주문명
        buyer_name: 'name', // 구매자 이름
        buyer_tel: 'phone', // 구매자 전화번호
        buyer_email: 'email@email.com', // 구매자 이메일
        buyer_addr: 'address', // 구매자 주소
        buyer_postcode: '01181', // 구매자 우편번호
    };
    IMP.request_pay(data, callback);
};
const callback = (response) => {
    const { success, error_msg } = response;
    if (success) {
        alert("결제 성공");
    } else {
        alert(`결제 실패: ${error_msg}`);
    }
};
