package com.kodlamaio.paymentService.business.concretes;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.paymentService.adapters.PosCheckService;
import com.kodlamaio.paymentService.business.abstracts.PaymentService;
import com.kodlamaio.paymentService.business.requests.CreatePaymentRequest;
import com.kodlamaio.paymentService.business.responses.CreatePaymentResponse;
import com.kodlamaio.paymentService.dataAccess.PaymentRepository;
import com.kodlamaio.paymentService.entities.Payment;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
	
	private PaymentRepository paymentRepository;
	private PosCheckService posCheckService;
	private ModelMapperService modelMapperService;

	@Override
	public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest) {
		
		Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		payment.setId(UUID.randomUUID().toString());
		paymentRepository.save(payment);
		CreatePaymentResponse response = modelMapperService.forResponse().map(payment, CreatePaymentResponse.class);
		return response;
	}

	@Override
	public void paymentReceived(String cardNumber, String cardName, String cvv, double price) {
		
		checkIfRentalTotalPrice(cardNumber,cardName,cvv,price);
		
	}
	
	private void checkIfRentalTotalPrice(String cardNumber,String cardName,String cvv,double price) {
		Payment payment = paymentRepository.findByCardNumberAndCardNameAndCvv(cardNumber, cardName, cvv);
		if (payment==null) {
			throw new BusinessException("invalid payment");
		}
		double amount = paymentRepository.findByCardNumber(cardNumber).getBalance();
		if (amount < price) {
			throw new BusinessException("insufficient balance");
		}
		posCheckService.pay();
		Payment paymentReceived = paymentRepository.findByCardNumber(cardNumber);
		paymentReceived.setBalance(amount - price);
		paymentRepository.save(paymentReceived);

	}

}
