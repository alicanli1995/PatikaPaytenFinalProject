package com.patikafinal.graduationproject.repository.credit;

import com.patikafinal.graduationproject.repository.models.Credit;
import com.patikafinal.graduationproject.repository.models.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CreditDtoImpl implements CreditDto{

    private final CreditJpaDto creditJpaDto;

    @Override
    public Double getCredit(Double creditLimit, Boolean creditStatus, Member member) {
        CreditEntity creditEntity = new CreditEntity();
        creditEntity.setCreditLimit(creditLimit);
        creditEntity.setCreditStatus(creditStatus);
        creditEntity.setTcNo(member.getTcNo());
        creditEntity.setCompleted("NO");
        return creditJpaDto.save(creditEntity).getCreditLimit();
    }

    @Override
    public List<Credit> findAll() {
        return creditJpaDto.findAll().stream()
                .map(Credit::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CreditEntity retrieve(Long id) {
        return creditJpaDto.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public CreditEntity getByIdEntities(Long id) {
        return creditJpaDto.getById(id);
    }

    @Override
    public void save(CreditEntity entity) {
        creditJpaDto.save(entity);
    }

    @Override
    public void deleteByMemberCredits(String tcNo) {
        creditJpaDto.deleteAllByTcNo(tcNo);
    }
}
