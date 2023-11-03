import { creditConfig } from "../../Config/Config";
import axios from 'axios'
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
    IMP.request_pay(data, 
        async requestPayResponse => {
            const { success, error_msg } = requestPayResponse;
            if (!success) {
              alert(`결제에 실패하였습니다. 에러 내용: ${error_msg}`);
              return;
            }
            // 이전 단계에서 구현한 결제정보 사후 검증 API 호출
            const res = await axios({
              url: "/payments/complete",
              method: "post",
              headers: { "Content-Type": "application/json" }, 
              data: { imp_uid: creditConfig.imp, merchant_uid: data.merchant_uid },
            });
            switch (res.status) {
              case "vbankIssued":
                // 가상계좌 발급 시 로직
                break;
              case "success":
                // axios.post()
                break;
            }
          });
};

