package com.kodlamaio.paymentService.business.concretes;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.dto.CustomerRequest;
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
	public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest,CustomerRequest customerRequest) {
		
		Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		payment.setId(UUID.randomUUID().toString());
		setCustomerInformation(customerRequest, payment);
		paymentRepository.save(payment);
		CreatePaymentResponse response = modelMapperService.forResponse().map(payment, CreatePaymentResponse.class);
		return response;
	}

	@Override
	public void paymentReceived(CreatePaymentRequest request) {
		
		checkIfRentalTotalPrice(request);
		
	}
	
	private void checkIfRentalTotalPrice(CreatePaymentRequest request) {
		Payment payment = paymentRepository.findByCardNumberAndCardNameAndCvv
				(request.getCardNumber(),request.getCardName(),request.getCvv());
		if (payment==null) {
			throw new BusinessException("invalid payment");
		}
		double amount = paymentRepository.findByCardNumber(request.getCardNumber()).getBalance();
		if (amount < request.getBalance()) {
			throw new BusinessException("insufficient balance");
		}
		posCheckService.pay();
		Payment paymentReceived = paymentRepository.findByCardNumber(request.getCardNumber());
		paymentReceived.setBalance(amount - request.getBalance());
		paymentRepository.save(paymentReceived);

	}
	
	private void setCustomerInformation(CustomerRequest customerRequest, Payment payment) {
        payment.setCustomerId(customerRequest.getCustomerId());
        payment.setCustomerUserName(customerRequest.getCustomerUserName());
        payment.setCustomerFirstName(customerRequest.getCustomerFirstName());
        payment.setCustomerLastName(customerRequest.getCustomerLastName());
        payment.setCustomerEmail(customerRequest.getCustomerEmail());
    }

}
