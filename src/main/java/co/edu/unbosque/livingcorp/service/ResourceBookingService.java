package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.ResourceBookingDTO;
import co.edu.unbosque.livingcorp.model.dto.UserDTO;
import co.edu.unbosque.livingcorp.model.entity.ResourceBooking;
import co.edu.unbosque.livingcorp.model.exception.NonpaymentException;
import co.edu.unbosque.livingcorp.model.exception.PRInvalidRequirementsException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.model.persistence.PersistenceDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ResourceBookingService {

    @Inject
    private PersistenceDAO<ResourceBooking, Integer> resourceBookingDAO;

    private ModelMapper mp;

    public ResourceBookingService(){
        mp = new ModelMapper();
    }

    public ResourceBookingDTO createResourceBooking(ResourceBookingDTO resourceBooking, UserDTO user) throws RepeatedObjectException, NonpaymentException, PRInvalidRequirementsException {
        if(resourceBooking.getPropertyResourceId().getAvailabily().equals("false")){
            throw new PRInvalidRequirementsException("Este resurso ya esta reservado.");
        }
        if(!resourceBooking.isPaymentComplete()){
            throw new NonpaymentException("No se ha realizado el pago.");
        }
        resourceBooking.setUserName(user);
        return mp.map(resourceBookingDAO.save(mp.map(resourceBooking, ResourceBooking.class)), ResourceBookingDTO.class);
    }

    public ResourceBookingDTO payingForResource(ResourceBookingDTO resourceBooking){
        resourceBooking.setPaymentComplete(true);
        resourceBooking.getPropertyResourceId().setAvailabily("false");
        return resourceBooking;
    }

    public ResourceBookingDTO calculatePayment(ResourceBookingDTO resourceBooking) throws PRInvalidRequirementsException {
        if(resourceBooking.getPropertyResourceId().getAvailabily().equals("false")){
            throw new PRInvalidRequirementsException("Este resurso ya esta reservado.");
        }
        if (!isBookingInAdvance(resourceBooking)) {
            throw new PRInvalidRequirementsException("La reserva debe realizarse con un mínimo de tres (3) días de anticipación.");
        }
        if (resourceBooking.getBookingStartDate().isAfter(resourceBooking.getBookingEndDate())) {
            throw new PRInvalidRequirementsException("La fecha de inicio de la reserva debe ser anterior a la fecha de finalización.");
        }
        if(!resourceBooking.getBookingDateTime().isEqual(resourceBooking.getBookingStartDate())){
            throw new PRInvalidRequirementsException("La fecha de la reserva debe ser igual a la fecha de inicio.");
        }
        long durationMinutes = Duration.between(resourceBooking.getBookingStartDate(), resourceBooking.getBookingEndDate()).toMinutes();
        if(durationMinutes >= (resourceBooking.getPropertyResourceId().getMinTimeH() * 60)){
            double costPerMinute = resourceBooking.getPropertyResourceId().getMinPrice() / (resourceBooking.getPropertyResourceId().getMinTimeH() * 60);

            double totalCost = durationMinutes * costPerMinute;

            double iva = totalCost * 0.19;
            double totalCostWithIVA = totalCost + iva;

            resourceBooking.setBookingCost(totalCostWithIVA);
            return resourceBooking;
        }else{
            throw new PRInvalidRequirementsException("El tiempo reservado debe ser mayor o igual al tiempo minimo de reserva del recurso.");
        }
    }

    public List<ResourceBookingDTO> showReservedResources(){
        return resourceBookingDAO.findAll().stream().map(resourceBooking -> mp.map(resourceBooking,ResourceBookingDTO.class)).collect(Collectors.toList());
    }

    public int getDuration(ResourceBookingDTO booking) {
        long timeDifference = Duration.between(booking.getBookingStartDate(), booking.getBookingEndDate()).toMinutes();
        return (int) (timeDifference);
    }

    private boolean isBookingInAdvance(ResourceBookingDTO resourceBooking) {
        return resourceBooking.getBookingDateTime().isAfter(LocalDateTime.now().plusDays(3));
    }
}
