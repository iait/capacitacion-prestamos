package com.eiv.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eiv.dao.LocalidadDao;
import com.eiv.entities.LocalidadEntity;
import com.eiv.interfaces.Localidad;
import com.eiv.repository.LocalidadRepository;
import com.eiv.testutils.ITestCfg;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LocalidadRepositoryIT.TestCfg.class)
public class LocalidadRepositoryIT {

    @Autowired private LocalidadRepository localidadRepository;
    
    @Test
    @Transactional
    public void givenLocalidadForm_whenCreate_thenNewProvincia() {
        
        Localidad localidad = new Localidad() {
            
            @Override
            public Long getProvinciaId() {
                return 1L;
            }
            
            @Override
            public String getNombre() {
                return "TEST";
            }
            
            @Override
            public String getCodigoPostal() {
                return "0000";
            }
        };
        
        LocalidadEntity localidadEntity = localidadRepository.save(localidad);
        Optional<LocalidadEntity> expected = localidadDao.findById(
                localidadEntity.getId());

        assertThat(expected).isNotEmpty();
        assertThat(expected.get()).isEqualTo(localidadEntity);

        assertThat(localidadEntity.getNombre()).isEqualTo(
                expected.get().getNombre());
        assertThat(localidadEntity.getCodigoPostal()).isEqualTo(
                expected.get().getCodigoPostal());
        assertThat(localidadEntity.getProvincia()).isEqualTo(
                expected.get().getProvincia());
    }

    @Test
    @Transactional
    public void givenLocalidadForm_whenSave_thenUpdateProvincia() {
        
        Localidad localidad = new Localidad() {
            
            @Override
            public Long getProvinciaId() {
                return 2L;
            }
            
            @Override
            public String getNombre() {
                return "TEST";
            }
            
            @Override
            public String getCodigoPostal() {
                return "0000";
            }
        };
        
        LocalidadEntity localidadEntity = localidadRepository.save(1L, localidad);
        Optional<LocalidadEntity> expected = localidadDao.findById(
                localidadEntity.getId());

        assertThat(expected).isNotEmpty();
        assertThat(expected.get()).isEqualTo(localidadEntity);

        assertThat(localidadEntity.getNombre()).isEqualTo(
                expected.get().getNombre());
        assertThat(localidadEntity.getCodigoPostal()).isEqualTo(
                expected.get().getCodigoPostal());
        assertThat(localidadEntity.getProvincia()).isEqualTo(
                expected.get().getProvincia());
    }

    @Test
    @Transactional
    public void givenLocalidadById_whenDelete_thenLocalidadNoExiste() {
        
        final Optional<LocalidadEntity> optional = localidadDao.findById(1L);
        
        localidadRepository.delete(optional.get().getId());
        
        final Optional<LocalidadEntity> expected = localidadDao.findById(1L);
        assertThat(expected).isEmpty();
    }
    
    @ComponentScan(basePackages = "com.eiv.repository")
    public static class TestCfg extends ITestCfg {
        
        @Bean
        public DataSource getDataSource() {
            JdbcDataSource ds = new JdbcDataSource();
            ds.setUrl("jdbc:h2:mem:testdb"
                    + ";INIT=runscript from 'src/test/resources/test-localidades.sql'");
            ds.setUser("sa");
            return ds;
        }
    }
    
    @Autowired private LocalidadDao localidadDao;
}
