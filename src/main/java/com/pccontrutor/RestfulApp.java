package com.pccontrutor;

import com.pccontrutor.model.Montagem;
import com.pccontrutor.model.Peca;
import com.pccontrutor.model.Usuario;
import com.pccontrutor.repository.MontagemRepository;
import com.pccontrutor.repository.PecaRepository;
import com.pccontrutor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class RestfulApp implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MontagemRepository montagemRepository;

	@Autowired
	private PecaRepository pecaRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestfulApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Peca peca1 = new Peca(
			"TUF B650m",
			"Asus",
			BigDecimal.valueOf(1498.99),
			"placa-mae",
			"AM5, DDR5, 4 slots RAM, 2 slots m2, 4 slots sata, 2 slots pci",
			"https://dlcdnwebimgs.asus.com/gain/6cc9dc8e-de19-4a3a-97bb-b27fe3a02d76/"
		);
		pecaRepository.save(peca1);

		Peca peca2 = new Peca(
			"Ryzen 5 7600",
			"AMD",
			BigDecimal.valueOf(1459),
			"cpu",
			"AM5, DDR5, 6 cores, 12 threads, 3.8 GHz, 5.1 GHz turbo, 65W",
			"https://images.kabum.com.br/produtos/fotos/405799/processador-amd-ryzen-5-7600-5-2ghz-max-turbo-cache-38mb-am5-6-nucleos-video-integrado-100-100001015box_1673290816_gg.jpg"
		);
		pecaRepository.save(peca2);

		Peca peca3 = new Peca(
			"O11 Dynamic Mini Branco",
			"Lian Li, Redragon",
			BigDecimal.valueOf(499.90),
			"gabinete",
			"ATX, até 8 fans, 2 usb 3.0, 1 usb 3.1, 1 usb 3.2, 1 usb 3.2 type c, 1 hdmi, 1 displayport, 1 audio, 1 mic",
			"https://cdn.awsli.com.br/800x800/1318/1318167/produto/214124434/1-97g4ir70di.png"
		);
		pecaRepository.save(peca3);

		Peca peca4 = new Peca(
			"GeForce RTX 3060",
			"Nvidia, Colorful iGame",
			BigDecimal.valueOf(1799.90),
			"gpu",
			"12GB, GDDR6, 192 bits, 3 fans, 1 hdmi, 3 displayport",
			"https://img.terabyteshop.com.br/produto/g/placa-de-video-colorful-igamer-geforce-rtx-3060-ultra-white-oc-12gb-l-v-gddr6-192bit_124850.jpg"
		);
		pecaRepository.save(peca4);

		Peca peca5 = new Peca(
			"Core Reactor 850W",
			"XPG",
			BigDecimal.valueOf(629.99),
			"fonte",
			"850W, 80 plus gold, modular, 100-240V, 47-63Hz, 1x ATX, 1x 24 pinos, 2x CPU 4 + 4 pinos, 6x PCI-E 6 + 2 pinos, 12x SATA, 4x Molex",
			"https://images.kabum.com.br/produtos/fotos/103282/fonte-xpg-core-reactor-850w-80-plus-gold-modular_fonte-xpg-core-reactor-850w-80-plus-gold-modular_1571154124_g.jpg"
		);
		pecaRepository.save(peca5);

		Peca peca6 = new Peca(
			"NV2 1TB",
			"Kingston",
			BigDecimal.valueOf(279.90),
			"armazenamento",
			"SSD 1TB, M.2 2280, PCIe NVMe, Leitura 3500 MB/s, Gravação 2100 MB/s",
			"https://images.kabum.com.br/produtos/fotos/380745/ssd-kingston-nv2-1-tb-m-2-2280-pcie-nvme-leitura-2-100-mb-s-e-gravacao-1-700-mb-s-snv2s-1000g_1666033119_g.jpg"
		);
		pecaRepository.save(peca6);

		Peca peca7 = new Peca(
			"Z 16GB",
			"Rise Mode",
			BigDecimal.valueOf(209.99),
			"ram",
			"1x16GB, DDR4, 3200MHz, C16, Branca",
			"https://images.kabum.com.br/produtos/fotos/383895/memoria-ram-rise-mode-z-16gb-3200mhz-ddr4-cl15-branco-rm-d4-16g-3200zw_1667509468_gg.jpg"
		);
		pecaRepository.save(peca7);

		Peca peca8 = new Peca(
			"ISENGARD 360mm White",
			"SuperFrame",
			BigDecimal.valueOf(359),
			"cooler-cpu",
			"ARGB, 360mm, Intel, AMD, Controladora, White",
			"https://img.terabyteshop.com.br/produto/g/water-cooler-superframe-isengard-argb-360mm-intel-amd-controladora-white-sf-w360w_167146.jpg"
		);
		pecaRepository.save(peca8);

		Peca peca9 = new Peca(
			"Kit 3x Hyrax 120mm",
			"Motospeed",
			BigDecimal.valueOf(154.99),
			"fan",
			"ARGB, 120mm, 3 fans, 1 controladora, 1 controle remoto, 1 hub, 1 cabo de extensão, 1 cabo de alimentação, 1 cabo de controle remoto, 12 parafusos, 1 manual, 1 cabo de conexão",
			"https://images.kabum.com.br/produtos/fotos/154969/kit-3x-cooler-fan-motospeed-hyrax-argb-120mm-para-gabinete-branco-hcl603w_1619183953_gg.jpg"
		);
		pecaRepository.save(peca9);

		Peca peca10 = new Peca(
			"Hércules",
			"SuperFrame",
			BigDecimal.valueOf(319.90),
			"gabinete",
			"ATX, até 10 fans, 2 usb 2.0, 2 usb 3.0, 1 HD Audio",
			"https://img.terabyteshop.com.br/produto/g/gabinete-gamer-superframe-hercules-full-tower-rgb-vidro-temperado-e-atx-black-sem-fonte-sem-fan_154018.jpg"
		);
		pecaRepository.save(peca10);

		Peca peca11 = new Peca(
			"AIR 100 Lite",
			"Montech",
			BigDecimal.valueOf(219.90),
			"gabinete",
			"ATX, até 8 fans, 1 usb 2.0, 2 usb 3.0. Inclui 2 fans de 120mm",
			"https://img.terabyteshop.com.br/produto/g/gabinete-gamer-montech-air-100-lite-mini-tower-vidro-temperado-black-e-atx-sem-fonte-com-4-fans_124958.png"
		);
		pecaRepository.save(peca11);

		Peca peca12 = new Peca(
			"x3 MESH",
			"Montech",
			BigDecimal.valueOf(269.90),
			"gabinete",
			"ATX, até 6 fans,  2 usb 2.0, 1 usb 3.0. Inclui 6 fans de 120mm",
			"https://img.terabyteshop.com.br/produto/g/gabinete-gamer-montech-x3-mesh-mid-tower-black-atx_125025.png"
		);
		pecaRepository.save(peca12);

		Peca peca13 = new Peca(
				"Kit 3x SickeFlow 120 ARGB",
				"Cooler Master",
				BigDecimal.valueOf(209.90),
				"fan",
				"ARGB, 120mm, 3 fans, MTTF 160.000 horas, 4 pinos, 4 parafusos 1 cabo de alimentação, 1 cabo de conexão1 manual",
				"https://media.pichau.com.br/media/catalog/product/cache/2f958555330323e505eba7ce930bdf27/m/f/mfx-b2dn-183pa-r1.jpg"
		);
		pecaRepository.save(peca13);

		Peca peca14 = new Peca(
				"RTX 4070 EX Gamer",
				"Nvidia, Galax",
				BigDecimal.valueOf(4148.99),
				"gpu",
				"12GB, GDDR6X, DLSS, Ray Tracing, Preto, ARGB",
				"https://images.kabum.com.br/produtos/fotos/447518/placa-de-video-rtx-4070-ex-gamer-galax-nvidia-geforce-12-gb-gddr6x-dlss-ray-tracing-preto-47nom7md7jeg_1681385526_gg.jpg"
		);
		pecaRepository.save(peca14);

		Peca peca15 = new Peca(
				"Vengeance LPX 32GB",
				"Corsair",
				BigDecimal.valueOf(539.99),
				"ram",
				"2x16GB, DDR4, 3600MHz, C18, Preto",
				"https://images.kabum.com.br/produtos/fotos/110827/memoria-corsair-vengeance-lpx-32gb-2x16gb-3600mhz-ddr4-c18-black-cmk32gx4m2d3600c18_1590062064_g.jpg"
		);
		pecaRepository.save(peca15);

		Peca peca16 = new Peca(
				"DC240 Black",
				"Aigo",
				BigDecimal.valueOf(289.90),
				"cooler-cpu",
				"Water Cooler, ARGB, 240mm, Preto, Intel-AMD, 1200/115X/1366/2011/2066/AM4/AM3+/AM3/AM2+/AM2/FM2+/FM2/FM1",
				"https://media.pichau.com.br/media/catalog/product/cache/2f958555330323e505eba7ce930bdf27/d/c/dc240bk.jpg"
		);
		pecaRepository.save(peca16);

		Usuario usuario1 = new Usuario(
			"max",
			"max@id.uff",
			"123"
		);
		usuarioRepository.save(usuario1);

		Usuario usuario2 = new Usuario(
			"carlos",
			"carlos@ic.uff",
			"456"
		);
		usuarioRepository.save(usuario2);

		Usuario usuario = new Usuario(
			"admin",
			"admin@host",
			"789"
		);
		usuarioRepository.save(usuario);

		usuario = new Usuario(
			"user1",
			"user1@email",
			"1"
		);
		usuarioRepository.save(usuario);

		usuario = new Usuario(
			"user2",
			"user2@email",
			"2"
		);
		usuarioRepository.save(usuario);

		usuario = new Usuario(
			"user3",
			"user3@email",
			"3"
		);
		usuarioRepository.save(usuario);

		usuario = new Usuario(
			"user4",
			"user4@email",
			"4"
		);
		usuarioRepository.save(usuario);

		usuario = new Usuario(
			"user5",
			"user5@email",
			"5"
		);
		usuarioRepository.save(usuario);

		usuario = new Usuario(
			"user6",
			"user6@email",
			"6"
		);
		usuarioRepository.save(usuario);

		usuario = new Usuario(
			"user7",
			"user7@email",
			"7"
		);
		usuarioRepository.save(usuario);

		usuario = new Usuario(
			"user8",
			"user8@email",
			"8"
		);
		usuarioRepository.save(usuario);

		usuario = new Usuario(
			"user9",
			"user9@email",
			"9"
		);
		usuarioRepository.save(usuario);

		Montagem montagem = new Montagem(
			"Meu PC",
			usuario1,
			peca3,
			peca5,
			peca1,
			peca2,
			peca4,
			peca6,
			peca7,
			peca8,
			peca9
		);
		montagemRepository.save(montagem);

		montagem = new Montagem(
				"Meu PC 2",
				usuario1,
				peca10,
				peca5,
				peca1,
				peca2,
				peca14,
				peca6,
				peca15,
				peca16,
				peca13
		);
		montagemRepository.save(montagem);

		montagem = new Montagem(
				"Meu PC 3",
				usuario1,
				peca11,
				peca5,
				peca1,
				peca2,
				peca14,
				peca6,
				peca15,
				peca16,
				peca13
		);
		montagemRepository.save(montagem);

		montagem = new Montagem(
				"Meu PC 4",
				usuario1,
				peca12,
				peca5,
				peca1,
				peca2,
				peca14,
				peca6,
				peca15,
				peca16,
				peca13
		);
		montagemRepository.save(montagem);

		montagem = new Montagem(
				"Meu PC 5",
				usuario1,
				peca3,
				peca5,
				peca1,
				peca2,
				peca4,
				peca6,
				peca7,
				peca8,
				peca9
		);
		montagemRepository.save(montagem);
	}
}
