package com.app.configuration;

import com.app.dto.CategoryDTO;
import com.app.dto.ProducerDTO;
import com.app.dto.ProductDTO;
import com.app.dto.TradeDTO;
import com.app.model.Category;
import com.app.model.Producer;
import com.app.model.Product;
import com.app.model.Trade;
import jdk.internal.org.objectweb.asm.util.TraceAnnotationVisitor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;



    @Configuration
    @ComponentScan("com.app")
    @EnableWebMvc
    public class AppConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {


        private ApplicationContext applicationContext;
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) /*throws BeansException*/ {
            this.applicationContext = applicationContext;
        }

        @Bean
        public ViewResolver viewResolver()
        {
            ThymeleafViewResolver resolver = new ThymeleafViewResolver();
            resolver.setTemplateEngine(templateEngine());
            resolver.setCharacterEncoding("UTF-8");
            return resolver;
        }

        @Bean
        public TemplateEngine templateEngine()
        {
            SpringTemplateEngine engine = new SpringTemplateEngine();
            engine.setEnableSpringELCompiler(true); //w thymeleaf uzywasz specjalnej skladni tzw
            //Spring Expression Langueage, ktory pozwala nam odwolywac sie do zmiennych w
            //aplikacja java z poziomu html
            engine.addDialect(new Java8TimeDialect()); //obsluga daty java 8, do zestawu klas thymeleaf
            //ktore uzywamy w aplikcaji dodany zostanie obiekt temporals z poziomu ktorego bedziemy
            //zarzadzac data java 8
            engine.setTemplateResolver(templateResolver());
            return engine;
        }

        private ITemplateResolver templateResolver()
        {
            SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
            resolver.setApplicationContext(applicationContext); //odwolanie sie do kontwkstu aplikacji
            resolver.setPrefix("/WEB-INF/views/");
            resolver.setSuffix(".html");
            resolver.setTemplateMode(TemplateMode.HTML);
            resolver.setCharacterEncoding("UTF-8");
            return resolver;
        }

        @Bean
        public ModelMapper modelMapper() {

            ModelMapper modelMapper = new ModelMapper();



            PropertyMap<Product, ProductDTO> productToProductDTO = new PropertyMap<Product, ProductDTO>() {
                @Override
                protected void configure() {
                    map().setProductId(source.getId());
                    map().setProductName(source.getName());
                    map().setProductPrice(source.getPrice());
                    map().setProductColour(source.getColour());
                    map().setProductDateOfProduction(source.getDateOfProduction());
                }
            };
            PropertyMap<Producer, ProducerDTO> producerProducerDTO = new PropertyMap<Producer, ProducerDTO>() {
                @Override
                protected void configure() {
                    map().setProducerId(source.getId());
                    map().setProducerName(source.getName());
                    map().setProducerCity(source.getCity());
                }
            };
            PropertyMap<Category, CategoryDTO> categoryCategoryDTO = new PropertyMap<Category, CategoryDTO>() {
                @Override
                protected void configure() {
                    map().setCategoryId(source.getId());
                    map().setCategoryName(source.getName());
                }
            };
            PropertyMap<Trade, TradeDTO> tradeTradeDTO = new PropertyMap<Trade, TradeDTO>() {
                @Override
                protected void configure() {
                    map().setTradeId(source.getId());
                    map().setTradeName(source.getName());

                }
            };

            modelMapper.addMappings(productToProductDTO);
            modelMapper.addMappings(producerProducerDTO);
            modelMapper.addMappings(categoryCategoryDTO);
            modelMapper.addMappings(tradeTradeDTO);
            return modelMapper;
        }
    }


