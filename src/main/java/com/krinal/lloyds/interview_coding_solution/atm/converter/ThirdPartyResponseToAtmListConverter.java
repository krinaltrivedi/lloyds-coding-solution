package com.krinal.lloyds.interview_coding_solution.atm.converter;

import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.Atm;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.Brand;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.OpenBankingAtmResponse;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.PostalAddress;
import com.krinal.lloyds.interview_coding_solution.atm.api.response.Address;
import com.krinal.lloyds.interview_coding_solution.atm.api.response.AtmDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ThirdPartyResponseToAtmListConverter implements ResponseConverter<OpenBankingAtmResponse, List<AtmDetail>> {
    @Override
    public List<AtmDetail> convert(final OpenBankingAtmResponse openBankingAtmResponse) {
        final List<AtmDetail> atmDetailList = new ArrayList<>();
        final var brandList = openBankingAtmResponse.getData().stream()
                .flatMap(data -> data.getBrand().stream())
                .collect(Collectors.toList());

        for (final Brand brand : brandList) {
            final var atmsForBrand = brand.getAtm();
            for (final Atm atm : atmsForBrand) {
                final var atmDetail = new AtmDetail();
                atmDetail.setBrandName(brand.getBrandName());
                atmDetail.setIdentification(atm.getIdentification());
                atmDetail.setSupportedCurrencies(atm.getSupportedCurrencies());
                final var address = convertAddress(atm.getLocation().getPostalAddress());
                atmDetail.setAddress(address);

                atmDetailList.add(atmDetail);
            }
        }
        return atmDetailList;
    }

    private Address convertAddress(final PostalAddress postalAddress) {
        final var address = new Address();
        address.setAddressLine1(postalAddress.getStreetName());
        address.setTown(postalAddress.getTownName());
        address.setCounty(getCountyFromPostalAddress(postalAddress));
        address.setCountry(postalAddress.getCountry());
        address.setPostcode(postalAddress.getPostcode());

        return address;
    }

    private String getCountyFromPostalAddress(final PostalAddress postalAddress) {
        final var resposnseCountrySubdivisionList = Optional.ofNullable(postalAddress)
                .map(PostalAddress::getCountrySubDivision)
                .orElse(null);

        if (resposnseCountrySubdivisionList != null) {
            return String.join(", ", resposnseCountrySubdivisionList);
        }
        return null;
    }
}
