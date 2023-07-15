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
			BigDecimal.valueOf(1500),
			"placa-mae",
			"AM5, DDR5, 4 slots RAM, 2 slots m2, 4 slots sata, 2 slots pci",
			"https://dlcdnwebimgs.asus.com/gain/6cc9dc8e-de19-4a3a-97bb-b27fe3a02d76/"
		);
		pecaRepository.save(peca1);

		Peca peca2 = new Peca(
			"Ryzen 5 7600",
			"AMD",
			BigDecimal.valueOf(1500),
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
			BigDecimal.valueOf(1500),
			"gpu",
			"12GB, GDDR6, 192 bits, 3 fans, 1 hdmi, 3 displayport",
			"https://img.terabyteshop.com.br/produto/g/placa-de-video-colorful-igamer-geforce-rtx-3060-ultra-white-oc-12gb-l-v-gddr6-192bit_124850.jpg"
		);
		pecaRepository.save(peca4);

		Usuario usuario1 = new Usuario(
		"max",
		"max@email",
		"123"
		);
		usuarioRepository.save(usuario1);

		Usuario usuario2 = new Usuario(
				"carlos",
				"carlos@uff",
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
			"pc estudo",
			usuario1,
				peca2,
				peca2,
				peca2,
				peca2,
				peca2,
				peca2,
				peca2,
				peca2,
				peca2
		);
		montagemRepository.save(montagem);

		montagem = new Montagem(
				"pc jogo",
				usuario2,
				peca1,
				peca1,
				peca1,
				peca1,
				peca1,
				peca1,
				peca1,
				peca1,
				peca1
		);
		montagemRepository.save(montagem);
	}
}
