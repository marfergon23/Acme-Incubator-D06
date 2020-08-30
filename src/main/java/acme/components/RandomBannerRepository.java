
package acme.components;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface RandomBannerRepository extends AbstractRepository {

	@Query("select count(b) from Banner b")
	int countAllBanners();

	@Query("select b From Banner b")
	List<Banner> findManyBanners(PageRequest pageRequest);

	default Banner findRandomBanner() {
		Banner res;
		int bannerCount, bannerIndex;
		ThreadLocalRandom random;
		PageRequest page;
		List<Banner> list;

		bannerCount = this.countAllBanners();
		random = ThreadLocalRandom.current();
		bannerIndex = random.nextInt(0, bannerCount);

		page = PageRequest.of(bannerIndex, 1);
		list = this.findManyBanners(page);
		res = list.isEmpty() ? null : list.get(0);

		return res;

	}
}
